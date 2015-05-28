package com.micromall.datacenter.dao.agent;

import com.micromall.datacenter.bean.agent.MallAgentApplyBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2015/5/28.
 */
public interface MallAgentApplyDao extends JpaRepository<MallAgentApplyBean, Integer> {

    @Query("select applyBean from MallAgentApplyBean applyBean where applyBean.customerId=?1 and (applyBean.name like %?2% or applyBean.mobile like %?2%)")
    Page<MallAgentApplyBean> findByCustomerId(int customerId, String searchKey, Pageable pageable);

    @Query("select applyBean from MallAgentApplyBean applyBean where applyBean.customerId=?1 and (applyBean.name like %?2% or applyBean.mobile like %?2%) and applyBean.applyStatus=?3 ")
    Page<MallAgentApplyBean> findByCustomerId(int customerId, String searchKey, int applyStatus, Pageable pageable);

    @Modifying
    @Query("update MallAgentApplyBean applyBean set applyBean.applyStatus=?1,applyBean.refuseReason=?2 where applyBean.applyId=?3")
    void updateApplyStatus(int applyStatus, String refuseReason, int applyId);
}
