package com.micromall.datacenter.dao.order;

import com.micromall.datacenter.bean.orders.MallOrderBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Query("select orderBean from MallOrderBean orderBean where orderBean.customerId=?1 and orderBean.deliverPath like %?2% and orderBean.orderId like %?3%")
    Page<MallOrderBean> findAll(int customerId, String agentId, String orderId, Pageable pageable);

    @Query("select orderBean from MallOrderBean orderBean where orderBean.customerId=?1 and (orderBean.ownerId=?2 and orderBean.sendId>0) or (orderBean.realShipId=?2 and orderBean.sendId=0) and orderBean.orderId like %?3%")
    Page<MallOrderBean> findOutOrder(int customerId, int agentId, String orderId, Pageable pageable);

    @Query("select orderBean from MallOrderBean orderBean where orderBean.customerId=?1 and orderBean.ownerId=?2 and orderBean.sendId=0 and orderBean.orderId like %?3%")
    Page<MallOrderBean> findInOrder(int customerId, int agentId, String orderId, Pageable pageable);
}
