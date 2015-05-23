package com.micromall.datacenter.service.agent;

import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2015/5/13.
 */
public interface MallAgentLevelService {
    MallAgentLevelBean save(MallAgentLevelBean bean);

    void delete(int levelId);

    List<MallAgentLevelBean> findByCustomerId(int customerId);

    MallAgentLevelBean findByLevelId(int levelId);

    boolean deletable(int levelId);

    int getCount(int customerId);

    MallAgentLevelBean findByCustomerIdAndSortNum(int customerId, int sortNum);

    List<MallAgentLevelBean> findByCustomerIdAndSortNumGreaterThan(int customerId, int sortNum);
}
