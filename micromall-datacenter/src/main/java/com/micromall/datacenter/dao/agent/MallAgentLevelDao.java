package com.micromall.datacenter.dao.agent;

import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2015/5/13.
 */
public interface MallAgentLevelDao extends JpaRepository<MallAgentLevelBean, Integer> {
    List<MallAgentLevelBean> findByCustomerId(int customerId);

    @Query("select count(agent.agentId) from MallAgentBean agent where agent.agentLevel.levelId=?1")
    int levelUsedNum(int levelId);

    @Query("select count(level.levelId) from MallAgentLevelBean level where level.customerId=?1")
    int getCount(int customerId);

    MallAgentLevelBean findByCustomerIdAndSortNum(int customerId, int sortNum);

    List<MallAgentLevelBean> findByCustomerIdAndSortNumGreaterThan(int customerId, int sortNum);
}
