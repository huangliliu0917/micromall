package com.micromall.datacenter.service.agent.impl;

import com.micromall.datacenter.bean.agent.MallGroupBean;
import com.micromall.datacenter.dao.agent.MallGroupDao;
import com.micromall.datacenter.service.agent.MallGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by allan on 2015/7/21.
 */
@Service
public class MallGroupServiceImpl implements MallGroupService {
    @Autowired
    private MallGroupDao dao;

    public MallGroupBean save(MallGroupBean groupBean) {
        return dao.save(groupBean);
    }

    public void delete(int groupId) {
        dao.delete(groupId);
    }

    public List<MallGroupBean> findAll(int customerId) {
        return dao.findByCustomerId(customerId);
    }

    public MallGroupBean findByGroupId(int groupId) {
        return dao.findOne(groupId);
    }

    public boolean deletable(int groupId) {
        int agentCount = dao.countByAgent("|" + groupId + "|");
        int goodCount = dao.countByGood("|" + groupId + "|");
        return agentCount == 0 && goodCount == 0;
    }
}
