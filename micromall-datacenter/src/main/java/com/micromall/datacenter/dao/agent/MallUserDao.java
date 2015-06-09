package com.micromall.datacenter.dao.agent;

import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallUserBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


/**
 * Created by Administrator on 2015/5/13.
 */
public interface MallUserDao extends JpaRepository<MallUserBean, Integer>, JpaSpecificationExecutor {

    @Modifying
    @Query("update MallUserBean u set u.isDelete=1 where u.userId=?1")
    void delete(int userId);

    @Query("select user from MallUserBean user where user.isDelete=0 and user.customerId=?1 and (user.userMobile like %?2% or user.userName like %?2%) and user.agent=?3")
    Page<MallUserBean> findAll(int customerId, String searchKey, MallAgentBean agentBean, Pageable pageable);

    @Query("select user from MallUserBean user where user.isDelete=0 and user.customerId=?1 and user.userMobile=?2 and user.agent=?3")
    MallUserBean findByUserNameAndAgent(int customerId, String userMobile, MallAgentBean agentBean);

    @Query("select count(user.userId) from MallUserBean user where user.isDelete=0 and user.customerId=?1 and user.userMobile=?2 and user.agent.agentId=?3 and user.userId<>?4")
    long userExists(int customerId, String userMobile, int agentId, int userId);
}
