package com.micromall.datacenter.service.agent;

import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.viewModel.agent.MallAgentSearchViewModel;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * �������߼�
 * Created by Administrator on 2015/5/13.
 */
public interface MallAgentService {
    /**
     * ��ӻ����޸Ĵ�������Ϣ
     *
     * @param bean
     * @param levelId
     * @return
     */
    MallAgentBean save(MallAgentBean bean, int levelId);

    /**
     * ͨ��������id�õ�������ʵ��
     *
     * @param agentId
     * @return
     */
    MallAgentBean findByAgentId(int agentId);

    /**
     * ��������ҳ����
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

    List<MallAgentBean> findByAgentLevel(int customerId, int agentLevel);

    boolean accountExist(String account, int customerId);

    Page<MallAgentBean> findBySearchKey(int customerId, String searchKey, int pageIndex, int pageSize, int superAgentId);

    MallAgentLevelBean findAgentLevel(int agentId);

    int updatePassword(String newPass, int agentId, String originalPass);

    int getUnderAgentNum(int superAgentId);

    void updateAddr(String newAddr, int agentId);

    /**
     * ͨ��account��customerid�õ�ʵ��
     *
     * @param agentAccount
     * @param customerId
     * @return
     */
    MallAgentBean findByAgentAccountAndCustomerId(String agentAccount, int customerId);
}
