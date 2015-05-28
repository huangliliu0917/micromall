package com.micromall.datacenter.service.agent;

import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallUserBean;
import org.springframework.data.domain.Page;


/**
 * Created by Administrator on 2015/5/13.
 */
public interface MallUserService {
    MallUserBean save(MallUserBean bean, int agentId);

    void delete(int userId);

    Page<MallUserBean> findAll(int customerId, int pageIndex, int pageSize, int agentId, String searchKey);

    MallUserBean findByUserId(int userId);

    MallUserBean findByUserNameAndAgent(int customerId, String userName, int agentId);
}
