package com.micromall.datacenter.dao.order;

import com.micromall.datacenter.bean.orders.WaitDeliverSnInfoBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2015/5/29.
 */
public interface WaitDeliverSnInfoDao extends JpaRepository<WaitDeliverSnInfoBean, Integer> {
    
}
