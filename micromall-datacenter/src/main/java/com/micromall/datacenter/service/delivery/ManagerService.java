package com.micromall.datacenter.service.delivery;

import com.micromall.datacenter.bean.delivery.ManagerBean;
import com.micromall.datacenter.viewModel.appModel.ManagerAppModel;
import org.springframework.data.domain.Page;

/**
 * 出库管理员-业务
 * Created by Administrator on 2015/6/23.
 */
public interface ManagerService {
    /**
     * 保存管理员信息
     *
     * @param bean
     * @return
     */
    ManagerBean save(ManagerBean bean);

    /**
     * 管理员列表（分页）
     *
     * @param customerId 商户ID
     * @param searchKey  关键字（用户名or手机）
     * @param pageIndex  页码
     * @param pageSize   每页数据量
     * @return
     */
    Page<ManagerBean> findAll(int customerId, String searchKey, int pageIndex, int pageSize);

    /**
     * 得到管理员实体通过id
     *
     * @param managerId
     * @return
     */
    ManagerBean findById(int managerId);

    /**
     * 修改管理员密码
     *
     * @param managerId 管理员id
     * @param oldPass   旧密码
     * @param newPass   新密码
     */
    void updatePassword(int managerId, String oldPass, String newPass);

    /**
     * 重置密码
     *
     * @param managerId 管理员id
     */
    void resetPassword(int managerId);

    /**
     * 修改管理员名称
     *
     * @param managerId 管理员id
     * @param newName   新姓名
     */
    void updateName(int managerId, String newName);

    /**
     * 删除管理员
     *
     * @param managerId 管理员id
     */
    void delete(int managerId);

    /**
     * 账号是否存在
     *
     * @param account
     * @return
     */
    boolean accountExists(String account);

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    ManagerBean login(String account, String password);

    /**
     * 得到appModel
     *
     * @param managerBean
     * @return
     */
    ManagerAppModel getAppModel(ManagerBean managerBean);
}
