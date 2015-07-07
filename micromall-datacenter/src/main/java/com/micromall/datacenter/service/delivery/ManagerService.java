package com.micromall.datacenter.service.delivery;

import com.micromall.datacenter.bean.delivery.ManagerBean;
import com.micromall.datacenter.viewModel.appModel.ManagerAppModel;
import org.springframework.data.domain.Page;

/**
 * �������Ա-ҵ��
 * Created by Administrator on 2015/6/23.
 */
public interface ManagerService {
    /**
     * �������Ա��Ϣ
     *
     * @param bean
     * @return
     */
    ManagerBean save(ManagerBean bean);

    /**
     * ����Ա�б���ҳ��
     *
     * @param customerId �̻�ID
     * @param searchKey  �ؼ��֣��û���or�ֻ���
     * @param pageIndex  ҳ��
     * @param pageSize   ÿҳ������
     * @return
     */
    Page<ManagerBean> findAll(int customerId, String searchKey, int pageIndex, int pageSize);

    /**
     * �õ�����Աʵ��ͨ��id
     *
     * @param managerId
     * @return
     */
    ManagerBean findById(int managerId);

    /**
     * �޸Ĺ���Ա����
     *
     * @param managerId ����Աid
     * @param oldPass   ������
     * @param newPass   ������
     */
    void updatePassword(int managerId, String oldPass, String newPass);

    /**
     * ��������
     *
     * @param managerId ����Աid
     */
    void resetPassword(int managerId);

    /**
     * �޸Ĺ���Ա����
     *
     * @param managerId ����Աid
     * @param newName   ������
     */
    void updateName(int managerId, String newName);

    /**
     * ɾ������Ա
     *
     * @param managerId ����Աid
     */
    void delete(int managerId);

    /**
     * �˺��Ƿ����
     *
     * @param account
     * @return
     */
    boolean accountExists(String account);

    /**
     * ��¼
     *
     * @param account
     * @param password
     * @return
     */
    ManagerBean login(String account, String password);

    /**
     * �õ�appModel
     *
     * @param managerBean
     * @return
     */
    ManagerAppModel getAppModel(ManagerBean managerBean);
}
