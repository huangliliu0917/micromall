package com.micromall.datacenter.service.delivery.impl;

import com.micromall.datacenter.bean.delivery.DeliverItemBean;
import com.micromall.datacenter.dao.delivery.DeliverItemDao;
import com.micromall.datacenter.service.delivery.DeliverItemService;
import com.micromall.datacenter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/23.
 */
@Service
public class DeliverItemServiceImpl implements DeliverItemService {
    @Autowired
    private DeliverItemDao dao;

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
    }

    @Transactional
    public void setDelivered(String orderId) {
        dao.setDelivered(orderId);
    }
}
