package com.micromall.datacenter.service.order;

import com.micromall.datacenter.bean.orders.WaitDeliverSnInfoBean;
import com.micromall.datacenter.pdaBean.SnInfoBean;

import java.util.List;

/**
 * Created by Administrator on 2015/5/29.
 */
public interface WaitDeliverSnInfoService {
    void batchSave(List<SnInfoBean> list);

    List<SnInfoBean> findAll(int customerId);
}
