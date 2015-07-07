package com.micromall.datacenter.service.delivery.impl;

import com.micromall.datacenter.bean.delivery.DeliverItemBean;
import com.micromall.datacenter.bean.delivery.ManagerBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.dao.delivery.DeliverItemDao;
import com.micromall.datacenter.service.delivery.BarCodeService;
import com.micromall.datacenter.service.delivery.DeliverItemService;
import com.micromall.datacenter.service.order.MallOrderService;
import com.micromall.datacenter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/23.
 */
@Service
public class DeliverItemServiceImpl implements DeliverItemService {
    @Autowired
    private DeliverItemDao dao;
    @Autowired
    private MallOrderService orderService;
    @Autowired
    private BarCodeService barCodeService;

    @Transactional
    public DeliverItemBean save(DeliverItemBean bean) {
        return dao.save(bean);
    }

    @Transactional(readOnly = true)
    public Page<DeliverItemBean> findAll(int customerId, String managerKey, String shipKey, String beginTime, String endTime, int pageIndex, int pageSize) {
        Date beginDate = null, endDate = null;
        if (StringUtils.isEmpty(beginTime)) {
            beginDate = new Date(0);
        } else {
            beginDate = StringUtil.DateFormat(beginTime, StringUtil.DATE_PATTERN);
        }
        if (StringUtil.isEmpty(endTime)) {
            endDate = new Date();
        } else {
            endDate = StringUtil.DateFormat(endTime, StringUtil.DATE_PATTERN);
        }
        return dao.findAll(customerId, managerKey, shipKey, beginDate, endDate, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "id")));
    }

    @Transactional(readOnly = true)
    public DeliverItemBean findByOrderId(String orderId) {
        DeliverItemBean itemBean = dao.findByOrderId(orderId);
        if (itemBean != null) {
            String[] proCodes = itemBean.getProCode().split(",");
            itemBean.setProList(proCodes);
        }
        return itemBean;
    }

    @Transactional
    public void setInvalidate(int id) {
        dao.setInvalidate(id);
        //将条码重新设置为未使用
        DeliverItemBean itemBean = dao.findOne(id);
        String[] proCodes = itemBean.getProCode().split(",");
        for (String proCode : proCodes) {
            barCodeService.unLockByCode(proCode);
        }
    }

    @Transactional
    public void setDelivered(String orderId) {
        dao.setDelivered(orderId);
    }

    public Map<String, Integer> deliverPro(int customerId, String proCodes, String orderId, String logiName, String logiNum, int managerId) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        MallOrderBean orderBean = orderService.findByOrderId(orderId);
        //判断是否锁定
        String[] proCodeArray = proCodes.split(",");
        boolean index = true;
        for (String proCode : proCodeArray) {
            if (barCodeService.codeUsable(proCode, orderBean.getGood().getGoodId())) {
                map.put(proCode, 1);
            } else {
                index = false;
                map.put(proCode, 0);
            }
        }
        //不可用，直接返回
        if (!index) {
            return map;
        }
        DeliverItemBean itemBean = new DeliverItemBean();
        itemBean.setOrderBean(orderBean);
        ManagerBean managerBean = new ManagerBean();
        managerBean.setId(managerId);
        itemBean.setManagerBean(managerBean);
        itemBean.setProCode(proCodes);
        itemBean.setAddTime(new Date());
        itemBean.setCustomerId(customerId);
        itemBean.setLogiName(logiName);
        itemBean.setLogiNum(logiNum);
        itemBean.setDeliverStatus(0);
        this.save(itemBean);
        //更新订单出货状态
        orderService.updateDeliver(orderId, 1);
        for (String proCode : proCodeArray) {
            //锁定条码
            barCodeService.lockedByCode(proCode);
        }
        return map;
    }
}
