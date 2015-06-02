package com.micromall.datacenter.service.order;

import com.micromall.datacenter.bean.orders.MallDeliverItemBean;
import com.micromall.datacenter.pdaBean.SnInfoBean;

import java.util.List;

/**
 * Created by Administrator on 2015/5/29.
 */
public interface MallDeliverItemService {
    void batchSave(List<SnInfoBean> list, int customerId);

    List<MallDeliverItemBean> findAll(int customerId, String agentMobile);

    void updateStatus(String[] snList);
}
