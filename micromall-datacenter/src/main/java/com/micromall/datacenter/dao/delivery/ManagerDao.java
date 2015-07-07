package com.micromall.datacenter.dao.delivery;

import com.micromall.datacenter.bean.delivery.ManagerBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 出库管理员
 * Created by Administrator on 2015/6/23.
 */

public interface ManagerDao extends JpaRepository<ManagerBean, Integer> {
    /**
     * 得到出库管理员列表（分页）
     *
     * @param customerId 商户ID
     * @param searchKey  搜索关键字（用户名，姓名）
     * @param pageable   分页
     * @return
     */
    @Query("select managerBean from ManagerBean managerBean where managerBean.customerId=?1 and managerBean.isDelete=0 and (managerBean.account like %?2% or managerBean.name like %?2%)")
    Page<ManagerBean> findAll(int customerId, String searchKey, Pageable pageable);

    /**
     * 修改密码
     *
     * @param managerId 管理员id
     * @param oldPass   旧密码
     * @param newPass   新密码
     */
    @Modifying
    @Query("update ManagerBean managerBean set managerBean.password=?3 where managerBean.id=?1 and managerBean.password=?2")
    void updatePassword(int managerId, String oldPass, String newPass);

    /**
     * 重置密码
     *
     * @param managerId 管理员id
     * @param newPass   新密码
     */
    @Modifying
    @Query("update ManagerBean managerBean set managerBean.password=?2 where managerBean.id=?1")
    void resetPassword(int managerId, String newPass);

    @Modifying
    @Query("update ManagerBean managerBean set managerBean.name=?2 where managerBean.id=?1")
    void updateName(int managerId, String newName);

    long countByAccount(String account);

    @Modifying
    @Query("update ManagerBean managerBean set managerBean.isDelete=1 where managerBean.id=?1")
    void setDelete(int managerId);

    @Query("select managerBean from ManagerBean managerBean where managerBean.account=?1 and managerBean.password=?2 and managerBean.isDelete=0")
    ManagerBean login(String account, String password);
}
