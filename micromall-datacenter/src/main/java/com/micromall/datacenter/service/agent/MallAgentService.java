package com.micromall.datacenter.service.agent;

import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.viewModel.agent.MallAgentSearchViewModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 代理商逻辑
 * Created by Administrator on 2015/5/13.
 */
public interface MallAgentService {
    /**
     * 添加或者修改代理商信息
     *
     * @param bean
     * @param levelId
     * @return
     */
    MallAgentBean save(MallAgentBean bean, int levelId);

    /**
     * 通过代理商id得到代理商实体
     *
     * @param agentId
     * @return
     */
    MallAgentBean findByAgentId(int agentId);

    /**
     * 带条件分页查找
     *
     * @param searchBean
     * @param pageIndex
     * @param pageSize
     * @param superAgentId
     * @return
     */
    Page<MallAgentBean> findAll(MallAgentSearchViewModel searchBean, int pageIndex, int pageSize, int superAgentId);

    void setDelete(int isDelete, int agentId);

    void updateAgentStatus(int agentStatus, String refuseReason, int agentId);

    MallAgentBean checkLogin(String account, String password, int customerId);

    List<MallAgentBean> findAgentByAgentLevel(MallAgentLevelBean levelBean, int customerId);

    boolean accountExist(String account, int customerId);

    Page<MallAgentBean> findBySearchKey(int customerId, String searchKey, int pageIndex, int pageSize, int superAgentId);

    MallAgentLevelBean findAgentLevel(int agentId);

    void updatePassword(String newPass, int agentId, String originalPass);

    int getUnderAgentNum(int superAgentId);

    void updateAddr(String newAddr, int agentId);
}
