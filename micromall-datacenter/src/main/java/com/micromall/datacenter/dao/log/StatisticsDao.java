package com.micromall.datacenter.dao.log;

import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.viewModel.log.AgentShipmentsViewModel;
import com.micromall.datacenter.viewModel.log.GoodShipmentsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/4.
 */
public interface StatisticsDao extends JpaRepository<MallOrderBean, String> {
    /**
     * 代理商出货量统计
     *
     * @param pageable
     * @return
     */
    @Query(value = "select new com.micromall.datacenter.viewModel.log.AgentShipmentsViewModel" +
            "(orderBean.realShipAgent.agentAccount as agentMobile," +
            "orderBean.realShipAgent.name as agentName," +
            "sum(orderBean.proNum) as proNum," +
            "orderBean.realShipAgent.agentLevel.levelName as levelName," +
            "orderBean.realShipAgent.authorizationCode as authorizationCode)" +
            " from MallOrderBean orderBean " +
            " where orderBean.customerId=?1 " +
//            "and orderBean.realShipAgent.agentLevel.levelId=?2" +
            " and orderBean.realShipAgent.addTime>=?3 and orderBean.realShipAgent.addTime<=?4" +
            " and (orderBean.realShipAgent.agentAccount  like %?2% or orderBean.realShipAgent.name like %?2%)" +
            " group by orderBean.realShipAgent.agentAccount,orderBean.realShipAgent.name," +
            "orderBean.realShipAgent.agentLevel.levelName,orderBean.realShipAgent.authorizationCode")
    Page<AgentShipmentsViewModel> getAgentShipments(int customerId, String agentMobile, Date beginTime, Date endTime, Pageable pageable);

    /**
     * 代理商出货量统计
     *
     * @param pageable
     * @return
     */
    @Query(value = "select new com.micromall.datacenter.viewModel.log.AgentShipmentsViewModel" +
            "(orderBean.realShipAgent.agentAccount as agentMobile," +
            "orderBean.realShipAgent.name as agentName," +
            "sum(orderBean.proNum) as proNum," +
            "orderBean.realShipAgent.agentLevel.levelName as levelName," +
            "orderBean.realShipAgent.authorizationCode as authorizationCode)" +
            " from MallOrderBean orderBean " +
            " where orderBean.customerId=?1 and orderBean.orderStatus=1" +
            " and orderBean.realShipAgent.agentLevel.levelId=?2" +
            " and orderBean.addTime>=?4 and orderBean.addTime<=?5" +
            " and (orderBean.realShipAgent.agentAccount  like %?3% or orderBean.realShipAgent.name like %?3%)" +
            " group by orderBean.realShipAgent.agentAccount,orderBean.realShipAgent.name," +
            "orderBean.realShipAgent.agentLevel.levelName,orderBean.realShipAgent.authorizationCode")
    Page<AgentShipmentsViewModel> getAgentShipments(int customerId, int levelId, String agentMobile, Date beginTime, Date endTime, Pageable pageable);

    @Query(value = "select new com.micromall.datacenter.viewModel.log.GoodShipmentsViewModel" +
            "(orderBean.good.goodName as goodName,orderBean.good.price as price," +
            "sum(orderBean.proNum) as num,orderBean.good.goodCode as goodCode)" +
            " from MallOrderBean orderBean" +
            " where orderBean.customerId=?1 and orderBean.orderStatus=1 and orderBean.realShipAgent is null" +
            " and orderBean.addTime>=?2 and orderBean.addTime<=?3" +
            " group by orderBean.good.goodName,orderBean.good.price,orderBean.good.goodCode")
    List<GoodShipmentsViewModel> getGoodShipments(int customerId, Date beginTime, Date endTime);
}
