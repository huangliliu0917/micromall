package com.micromall.datacenter.service.order.impl;

import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.bean.orders.MallOrderItemBean;
import com.micromall.datacenter.dao.order.MallOrderDao;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.good.MallGoodsService;
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
import java.util.*;

/**
 * Created by Administrator on 2015/5/14.
 */
@Service
public class MallOrderServiceImpl implements MallOrderService {
    @Autowired
    private MallOrderDao dao;
    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallGoodsService goodsService;

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
    public Page<MallOrderBean> findAll(final MallOrderSearchViewModel searchViewModel, int pageIndex, int pageSize, final int customerId) {
        Specification<MallOrderBean> specification = new Specification<MallOrderBean>() {
            public Predicate toPredicate(Root<MallOrderBean> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(root.get("customerId").as(Integer.class), customerId));
                if (searchViewModel != null) {
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
                    if (StringUtil.isNotEmpty(searchViewModel.getShipName())) {
                        list.add(criteriaBuilder.like(root.get("shipName").as(String.class), searchViewModel.getShipName()));
                    }
                    if (StringUtil.isNotEmpty(searchViewModel.getShipMobile())) {
                        list.add(criteriaBuilder.like(root.get("shipMobile").as(String.class), searchViewModel.getShipMobile()));
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
        Set<MallOrderItemBean> orderItems = new HashSet<MallOrderItemBean>();
        for (String proCode : proCodes) {
            MallOrderItemBean itemBean = new MallOrderItemBean();
            itemBean.setProCode(proCode);
            itemBean.setCustomerId(orderBean.getCustomerId());
            itemBean.setOrdersBean(orderBean);
            orderItems.add(itemBean);
        }
        orderBean.setOrderItems(orderItems);
        orderBean.setShipInfo(shipInfo);
        orderBean.setOrderStatus(1);
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

        //重新设置相应的代理商价格
        String deliverPath = orderBean.getDeliverPath().substring(1, orderBean.getDeliverPath().length() - 2); //形如：3|2|1
        String[] tempInfo = deliverPath.split("\\|");
        MallAgentBean agentBean = agentService.findByAgentId(Integer.parseInt(tempInfo[tempInfo.length - 1])); //已该代理商等级为准
        double price = goodsService.getPriceByAgent(agentBean.getAgentLevel().getLevelId(), orderBean.getGood().getPriceInfo()); //得到相应代理商等级的价格

        orderBean.setTotalPrice(orderBean.getProNum() * price);
        orderBean.setDeliverPath("|" + deliverPath + "|" + transferTo + "|");
        dao.save(orderBean);
    }

    public String createOrderId(int customerId) {
        return StringUtil.DateFormat(new Date(), "yyyyMMddHHmmSS") + (int) (Math.random() * 89 + 10);
    }

    /**
     * 得到代理商的订单列表
     *
     * @param customerId
     * @param agentId
     * @param pageIndex
     * @param pageSize
     * @param orderType
     * @return
     */
    public Page<MallOrderBean> findAll(int customerId, int agentId, int pageIndex, int pageSize, int orderType, String orderId) {
        Page<MallOrderBean> pageInfo = null;
        if (orderType == 0) {
            pageInfo = dao.findAll(customerId, "|" + agentId + "|", orderId, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "addTime"))); //全部
        } else if (orderType == 1) {
            pageInfo = dao.findInOrder(customerId, agentId, orderId, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "addTime"))); //进货
        } else {
            pageInfo = dao.findOutOrder(customerId, agentId, orderId, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "addTime"))); //出货
        }
        //不同代理商登录针对同一订单应该看到下级代理商的订单金额
        for (MallOrderBean orderBean : pageInfo.getContent()) {
            if (orderBean.getSendId() == 0) {
                String[] deliverPath = orderBean.getDeliverPath().substring(1, orderBean.getDeliverPath().length() - 1).split("\\|");
                int index = 0;
                for (int i = 0; i < deliverPath.length; i++) {
                    if (Integer.parseInt(deliverPath[i]) == agentId) {
                        index = i;
                        break;
                    }
                }
                int resultIndex = index;
//                if (orderBean.getOwnerId() != agentId) {
                if (deliverPath.length > 2) {
                    resultIndex = index - 1;
                }
                double price = goodsService.getPriceByAgent(agentService.findAgentLevel(Integer.parseInt(deliverPath[resultIndex])).getLevelId(), goodsService.findPriceInfo(orderBean.getGood().getGoodId()));
                orderBean.setTotalPrice(orderBean.getProNum() * price);
            }
        }
        return pageInfo;
    }

    /**
     * 得到发货通知数量
     *
     * @param customerId
     * @param agentId
     * @return
     */
    public int findCountInOrder(int customerId, int agentId) {
        return dao.findCountInOrder(customerId, agentId);
    }
}
