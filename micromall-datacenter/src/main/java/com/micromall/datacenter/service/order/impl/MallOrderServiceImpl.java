package com.micromall.datacenter.service.order.impl;

import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.bean.orders.MallOrderItemBean;
import com.micromall.datacenter.dao.order.MallOrderDao;
import com.micromall.datacenter.service.order.MallOrderService;
import com.micromall.datacenter.utils.StringUtil;
import com.micromall.datacenter.viewModel.order.MallOrderSearchViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/5/14.
 */
@Service
public class MallOrderServiceImpl implements MallOrderService {
    @Autowired
    private MallOrderDao dao;

    @Transactional
    public MallOrderBean create(MallOrderBean bean, int goodId) {
        if (StringUtil.isEmpty(bean.getOrderId())) {
            bean.setOrderId(this.createOrderId(bean.getCustomerId()));
        }
        MallGoodBean goodBean = new MallGoodBean();
        goodBean.setGoodId(goodId);
        bean.setGood(goodBean);
        return dao.save(bean);
    }

    @Transactional(readOnly = true)
    public Page<MallOrderBean> findAll(final MallOrderSearchViewModel searchViewModel, int pageIndex, int pageSize) {
        Specification<MallOrderBean> specification = new Specification<MallOrderBean>() {
            public Predicate toPredicate(Root<MallOrderBean> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (searchViewModel != null) {
                    list.add(criteriaBuilder.equal(root.get("customerId").as(Integer.class), searchViewModel.getCustomerId()));
                    if (StringUtil.isNotEmpty(searchViewModel.getOrderId())) {
                        list.add(criteriaBuilder.like(root.get("orderId").as(String.class), "%" + searchViewModel.getOrderId() + "%"));
                    }
                    if (StringUtil.isNotEmpty(searchViewModel.getBeginTime())) {
                        list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("addTime").as(Date.class), StringUtil.DateFormat(searchViewModel.getBeginTime(), StringUtil.DATE_PATTERN)));
                    }
                    if (StringUtil.isNotEmpty(searchViewModel.getEndTime())) {
                        list.add(criteriaBuilder.lessThanOrEqualTo(root.get("addTime").as(Date.class), StringUtil.DateFormat(searchViewModel.getEndTime(), StringUtil.DATE_PATTERN)));
                    }
                    if (searchViewModel.getOrderStatus() != -1) {
                        list.add(criteriaBuilder.equal(root.get("orderStatus").as(Integer.class), searchViewModel.getOrderStatus()));
                    }
                    if (searchViewModel.getOwnerId() > 0) {
                        list.add(criteriaBuilder.equal(root.get("ownerId").as(Integer.class), searchViewModel.getOwnerId()));
                    }
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        return dao.findAll(specification, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "addTime")));
    }

    @Transactional(readOnly = true)
    public MallOrderBean findByOrderId(String orderId) {
        return dao.findOne(orderId);
    }

    /**
     * 更新订单状态
     *
     * @param orderId     订单号
     * @param orderStatus 状态值
     */
    @Transactional
    public void updateOrderStatus(String orderId, int orderStatus) {
        dao.updateOrderStatus(orderStatus, orderId);
    }

    /**
     * 确认发货
     *
     * @param orderBean
     * @param proCodes
     * @param shipInfo
     */
    @Transactional
    public void confirmShip(MallOrderBean orderBean, String[] proCodes, String shipInfo) {
        List<MallOrderItemBean> orderItems = new ArrayList<MallOrderItemBean>();
        for (String proCode : proCodes) {
            MallOrderItemBean itemBean = new MallOrderItemBean();
            itemBean.setProCode(proCode);
            itemBean.setCustomerId(orderBean.getCustomerId());
            itemBean.setOrdersBean(orderBean);
            orderItems.add(itemBean);
        }
        orderBean.setOrderItems(orderItems);
        orderBean.setShipInfo(shipInfo);
        dao.save(orderBean);
    }

    /**
     * 给上级代理代发货
     *
     * @param orderId    订单号
     * @param transferTo 提交的上级代理ID
     */
    @Transactional
    public void transferOrder(String orderId, int transferTo) {
        MallOrderBean orderBean = dao.findOne(orderId);
        orderBean.setRealShipId(transferTo);
        orderBean.setDeliverPath(orderBean.getDeliverPath() + "," + transferTo);
        dao.save(orderBean);
    }

    public String createOrderId(int customerId) {
        return StringUtil.DateFormat(new Date(), "yyyyMMddHHmmSS") + (int) (Math.random() * 89 + 10);
    }

    public Page<MallOrderBean> findAll(int customerId, int agentId, int pageIndex, int pageSize, int orderType) {
        if (orderType == 0) {
            return dao.findAll(customerId, agentId, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "addTime"))); //全部
        } else if (orderType == 1) {
            return dao.findInOrder(customerId, agentId, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "addTime"))); //进货
        } else {
            return dao.findOutOrder(customerId, agentId, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "addTime"))); //出货
        }
    }
}
