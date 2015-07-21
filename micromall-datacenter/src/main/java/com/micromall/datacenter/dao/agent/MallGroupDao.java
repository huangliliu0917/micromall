package com.micromall.datacenter.dao.agent;

import com.micromall.datacenter.bean.agent.MallGroupBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by allan on 2015/7/21.
 */
public interface MallGroupDao extends JpaRepository<MallGroupBean, Integer> {
    List<MallGroupBean> findByCustomerId(int customerId);

    @Query("select count(agent.agentId) from MallAgentBean agent where agent.groups like %?1%")
    int countByAgent(String groupId);

    @Query("select count(good.goodId) from MallGoodBean good where good.groups like %?1%")
    int countByGood(String groupId);
}
