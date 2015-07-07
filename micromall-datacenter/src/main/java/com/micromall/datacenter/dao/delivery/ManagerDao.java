package com.micromall.datacenter.dao.delivery;

import com.micromall.datacenter.bean.delivery.ManagerBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * �������Ա
 * Created by Administrator on 2015/6/23.
 */

public interface ManagerDao extends JpaRepository<ManagerBean, Integer> {
    /**
     * �õ��������Ա�б���ҳ��
     *
     * @param customerId �̻�ID
     * @param searchKey  �����ؼ��֣��û�����������
     * @param pageable   ��ҳ
     * @return
     */
    @Query("select managerBean from ManagerBean managerBean where managerBean.customerId=?1 and managerBean.isDelete=0 and (managerBean.account like %?2% or managerBean.name like %?2%)")
    Page<ManagerBean> findAll(int customerId, String searchKey, Pageable pageable);

    /**
     * �޸�����
     *
     * @param managerId ����Աid
     * @param oldPass   ������
     * @param newPass   ������
     */
    @Modifying
    @Query("update ManagerBean managerBean set managerBean.password=?3 where managerBean.id=?1 and managerBean.password=?2")
    void updatePassword(int managerId, String oldPass, String newPass);

    /**
     * ��������
     *
     * @param managerId ����Աid
     * @param newPass   ������
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
