package com.micromall.datacenter.service.agent.impl;

import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.dao.agent.MallAgentLevelDao;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2015/5/13.
 */
@Service
@Transactional
public class MallAgentLevelServiceImpl implements MallAgentLevelService {
    @Autowired
    private MallAgentLevelDao dao;

    public MallAgentLevelBean save(MallAgentLevelBean bean) {
        int count = getCount(bean.getCustomerId());
        bean.setSortNum(count);
        return dao.save(bean);
    }

    public void delete(int levelId) {
        dao.delete(levelId);
        dao.flush();
    }

    @Transactional(readOnly = true)
    public List<MallAgentLevelBean> findByCustomerId(int customerId) {
        return dao.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public MallAgentLevelBean findByLevelId(int levelId) {
        return dao.findOne(levelId);
    }

    @Transactional(readOnly = true)
    public boolean deletable(int levelId) {
        int num = dao.levelUsedNum(levelId);
        return num == 0 ? true : false;
    }

    @Transactional(readOnly = true)
    public int getCount(int customerId) {
        return dao.getCount(customerId);
    }

    @Transactional(readOnly = true)
    public MallAgentLevelBean findByCustomerIdAndSortNum(int customerId, int sortNum) {
        return dao.findByCustomerIdAndSortNum(customerId, sortNum);
    }

    @Transactional(readOnly = true)
    public List<MallAgentLevelBean> findByCustomerIdAndSortNumGreaterThan(int customerId, int sortNum) {
        return dao.findByCustomerIdAndSortNumGreaterThan(customerId, sortNum);
    }
}
