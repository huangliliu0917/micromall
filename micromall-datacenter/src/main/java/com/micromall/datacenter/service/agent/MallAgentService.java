package com.micromall.datacenter.service.agent;

import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.viewModel.agent.MallAgentSearchViewModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2015/5/13.
 */
public interface MallAgentService {
    MallAgentBean save(MallAgentBean bean, int levelId);

    MallAgentBean findByAgentId(int agentId);

    Page<MallAgentBean> findAll(MallAgentSearchViewModel searchBean, int pageIndex, int pageSize, int superAgentId);

    void setDelete(int isDelete, int agentId);

    void updateAgentStatus(int agentId, int agentStatus);

    MallAgentBean checkLogin(String account, String password, int customerId);

    List<MallAgentBean> findAgentByAgentLevel(MallAgentLevelBean levelBean, int customerId);

    boolean accountExist(String account, int customerId);

    Page<MallAgentBean> findBySearchKey(int customerId, String searchKey, int pageIndex, int pageSize, int superAgentId);
}
