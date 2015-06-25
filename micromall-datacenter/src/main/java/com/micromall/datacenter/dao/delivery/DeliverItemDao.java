package com.micromall.datacenter.dao.delivery;

import com.micromall.datacenter.bean.delivery.DeliverItemBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * Created by Administrator on 2015/6/23.
 */
public interface DeliverItemDao extends JpaRepository<DeliverItemBean, Integer> {
    @Query("select deliverItem from DeliverItemBean deliverItem where deliverItem.customerId=?1 and " +
            "(deliverItem.managerBean.name like %?2% or deliverItem.managerBean.account like %?2%) and " +
            "(deliverItem.orderBean.shipName like %?3% or deliverItem.orderBean.shipMobile like %?3%) and deliverItem.addTime between ?4 and ?5")
    Page<DeliverItemBean> findAll(int customerId, String managerKey, String shipKey, Date beginTime, Date endTime, Pageable pageable);

    @Query("select deliverItem from DeliverItemBean deliverItem where deliverItem.orderBean.orderId=?1 and deliverItem.deliverStatus=0")
    DeliverItemBean findByOrderId(String orderId);

    /**
     * ÉèÎª×÷·Ï
     *
     * @param id
     */
    @Modifying
    @Query("update DeliverItemBean deliverItem set deliverItem.deliverStatus=1 where deliverItem.id=?1")
    void setInvalidate(int id);

    @Modifying
    @Query("update DeliverItemBean deliverItem set deliverItem.deliverStatus=2 where deliverItem.orderBean.orderId=?1 and deliverItem.deliverStatus=0")
    void setDelivered(String orderId);
}
