package com.micromall.datacenter.service.agent.impl;

import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallUserBean;
import com.micromall.datacenter.dao.agent.MallUserDao;
import com.micromall.datacenter.service.agent.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2015/5/13.
 */
@Service
@Transactional
public class MallUserServiceImpl implements MallUserService {
    @Autowired
    private MallUserDao dao;

    public MallUserBean save(MallUserBean bean) {
        return dao.save(bean);
    }

    public void delete(int userId) {
        dao.delete(userId);
    }

    public Page<MallUserBean> findAll(int customerId, int pageIndex, int pageSize, int agentId, String searchKey) {
        MallAgentBean agentBean = new MallAgentBean();
        agentBean.setAgentId(agentId);
        return dao.findAll(customerId, searchKey, agentBean, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "userId")));
    }


}
