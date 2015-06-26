package com.micromall.datacenter.dao.order;

import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.bean.orders.MallOrderItemBean;
import com.micromall.datacenter.viewModel.log.AgentShipmentsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;


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

    @Query("select orderBean from MallOrderBean orderBean where orderBean.customerId=?1 and ((orderBean.ownerId=?2 and orderBean.sendId>0) or (orderBean.realShipAgent.agentId=?2 and orderBean.sendId=0)) and orderBean.orderId like %?3%")
    Page<MallOrderBean> findOutOrder(int customerId, int agentId, String orderId, Pageable pageable);

    @Query("select orderBean from MallOrderBean orderBean where orderBean.customerId=?1 and orderBean.ownerId=?2 and orderBean.sendId=0 and (orderBean.realShipAgent is null or orderBean.realShipAgent.agentId<>orderBean.ownerId) and orderBean.orderId like %?3%")
    Page<MallOrderBean> findInOrder(int customerId, int agentId, String orderId, Pageable pageable);

    @Query("select count(orderBean.orderId) from MallOrderBean orderBean where orderBean.orderStatus=0 and orderBean.customerId=?1 and ((orderBean.ownerId=?2 and orderBean.sendId>0) or (orderBean.realShipAgent.agentId=?2 and orderBean.sendId=0))")
    int findCountInOrder(int customerId, int agentId);

    @Query("select itemBean from MallOrderItemBean itemBean where itemBean.customerId=?1 and itemBean.proCode like %?2%")
    Page<MallOrderItemBean> findBySnCode(int customerId, String snCode, Pageable pageable);

    @Query("select sum(orderBean.proNum) from MallOrderBean orderBean where orderBean.orderStatus=1 and orderBean.realShipAgent is null and orderBean.addTime between ?1 and ?2")
    int getShipments(Date beginTime, Date endTime);

    /**
     * 设置出货状态
     *
     * @param orderId
     * @param deliverStatus
     */
    @Modifying
    @Query("update MallOrderBean orderBean set orderBean.deliverStatus=?2 where orderBean.orderId=?1")
    void updateDeliver(String orderId, int deliverStatus);

    /**
     * 未出货或已出货订单数量
     *
     * @param customerId
     * @param deliverStatus
     * @return
     */
    int countByCustomerIdAndDeliverStatusAndRealShipAgentIsNull(int customerId, int deliverStatus);

    /**
     * 今日订单数量
     *
     * @param customerId
     * @param today
     * @return
     */
    int countByCustomerIdAndAddTimeGreaterThanAndRealShipAgentIsNull(int customerId, Date today);

    /**
     * 订单号码数量（判断生成的订单号是否可用）
     *
     * @param orderId
     * @return
     */
    int countByOrderId(String orderId);
}
