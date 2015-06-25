package com.micromall.datacenter.service.delivery.impl;

import com.micromall.datacenter.bean.config.MallBaseConfigBean;
import com.micromall.datacenter.bean.delivery.ManagerBean;
import com.micromall.datacenter.dao.delivery.ManagerDao;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import com.micromall.datacenter.service.delivery.ManagerService;
import com.micromall.datacenter.utils.ResourceServer;
import com.micromall.datacenter.viewModel.appModel.ManagerAppModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2015/6/23.
 */
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao dao;
    @Autowired
    private ResourceServer resourceServer;
    @Autowired
    private MallBaseConfigService configService;

    public ManagerBean save(ManagerBean bean) {
        return dao.save(bean);
    }

    public Page<ManagerBean> findAll(int customerId, String searchKey, int pageIndex, int pageSize) {
        return dao.findAll(customerId, searchKey, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "id")));
    }

    public void updatePassword(int managerId, String oldPass, String newPass) {
        dao.updatePassword(managerId, oldPass, newPass);
    }

    public void resetPassword(int managerId, String newPass) {
        dao.resetPassword(managerId, newPass);
    }

    public void updateName(int managerId, String newName) {
        dao.updateName(managerId, newName);
    }

    public void delete(int managerId) {
        dao.setDelete(managerId);
    }

    public boolean accountExists(String account) {
        return dao.countByAccount(account) > 0 ? true : false;
    }

    public ManagerBean login(String account, String password) {
        return dao.login(account, password);
    }

    public ManagerAppModel getAppModel(ManagerBean managerBean) {
        if (managerBean == null)
            return null;
        ManagerAppModel appModel = new ManagerAppModel();
        appModel.setId(managerBean.getId());
        appModel.setName(managerBean.getName());
        appModel.setAccount(managerBean.getAccount());
        appModel.setCustomerId(managerBean.getCustomerId());
        MallBaseConfigBean configBean = configService.findByCustomerId(managerBean.getCustomerId());
        appModel.setCustomerLogo(resourceServer.resourceUri(configBean.getLogo()));
        appModel.setCustomerName(configBean.getTitle());
        return appModel;
    }
}
