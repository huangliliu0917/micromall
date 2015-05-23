package com.micromall.datacenter.dao.order;

import com.micromall.datacenter.bean.orders.MallOrderBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2015/5/14.
 */
public interface MallOrderDao extends JpaRepository<MallOrderBean, String>, JpaSpecificationExecutor {

    @Modifying
    @Query("update MallOrderBean o set o.orderStatus=?1 where o.orderId=?2")
    void updateOrderStatus(int orderStatus, String orderId);

    @Modifying
    @Query("delete from MallOrderItemBean items where items.ordersBean=?1")
    void removeOrderItems(MallOrderBean bean);
}
