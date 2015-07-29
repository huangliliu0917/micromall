package com.micromall.datacenter.service.agent;

import com.micromall.datacenter.bean.agent.MallAgentApplyBean;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2015/5/28.
 */
public interface MallAgentApplyService {
    MallAgentApplyBean save(MallAgentApplyBean applyBean);

    /**
     * handler apply
     *
     * @param applyId
     * @param superAgentId
     * @param password
     * @param levelId
     * @param applyStatus
     * @param refuseReason
     */
    MallAgentApplyBean updateApplyStataus(int applyId, int superAgentId, String password, int levelId, int applyStatus, String refuseReason, String groups);

    Page<MallAgentApplyBean> findByCustomerId(int customerId, String searchKey, int applyStatus, int pageIndex, int pageSize);

    MallAgentApplyBean findByApplyId(int applyId);
}
