package com.micromall.datacenter.dao.agent;

import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by Administrator on 2015/5/13.
 */
public interface MallAgentDao extends JpaRepository<MallAgentBean, Integer>, JpaSpecificationExecutor {
    @Modifying
    @Query("update MallAgentBean agent set agent.isDelete=?1 where agent.agentId=?2")
    void setDelete(int isDelete, int agentId);

    @Modifying
    @Query("update MallAgentBean agent set agent.agentStatus=?1 where agent.agentId=?2")
    void updateAgentStatus(int agentStatus, int agentId);

    @Query("select agent from MallAgentBean agent where agent.agentAccount=?1 and agent.agentPassword=?2 and agent.customerId=?3 and agent.isDelete=0 and agent.agentStatus=1")
    MallAgentBean checkLogin(String account, String password, int customerId);

    @Query("select agent from MallAgentBean agent where agent.agentStatus=1 and agent.customerId=?1 and agent.agentLevel=?2")
    List<MallAgentBean> findByAgentLevel(int customerId, MallAgentLevelBean agentLevel);

    @Query("select count(agent.agentId) from MallAgentBean agent where agent.agentAccount=?1 and agent.customerId=?2")
    int accountExist(String account, int customerId);

    @Query("select agent from MallAgentBean agent where agent.isDelete=0 and agent.customerId=?1 and (agent.agentAccount like %?2% or agent.name like %?2%) and agent.superAgentId=?3")
    Page<MallAgentBean> findBySearchKey(int customerId, String searchKey, int superAgentId, Pageable pageable);

    @Query("select agent.agentLevel from MallAgentBean agent where agent.agentId=?1")
    MallAgentLevelBean findAgentLevel(int agentId);
}
