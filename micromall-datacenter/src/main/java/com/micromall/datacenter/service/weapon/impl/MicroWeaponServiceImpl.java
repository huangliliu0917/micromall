package com.micromall.datacenter.service.weapon.impl;

import com.micromall.datacenter.bean.weapon.MicroWeaponBean;
import com.micromall.datacenter.dao.weapon.MicroWeaponDao;
import com.micromall.datacenter.service.weapon.MicroWeaponService;
import com.micromall.datacenter.utils.ResourceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by Administrator on 2015/5/27.
 */
@Service
public class MicroWeaponServiceImpl implements MicroWeaponService {
    @Autowired
    private MicroWeaponDao dao;
    @Autowired
    private ResourceServer resourceServer;

    @Transactional
    public MicroWeaponBean save(MicroWeaponBean weaponBean) {
        return dao.save(weaponBean);
    }

    @Transactional(readOnly = true)
    public Page<MicroWeaponBean> findByCustomerIdAndWeaponType(int customerId, int weaponType, int pageIndex, int pageSize) {
        Page<MicroWeaponBean> pageInfo = dao.findByCustomerIdAndWeaponType(customerId, weaponType, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "weaponId")));
        return setImgs(pageInfo);
    }

    @Transactional(readOnly = true)
    public MicroWeaponBean findByWeaponId(int weaponId) {
        MicroWeaponBean weaponBean = dao.findOne(weaponId);
        weaponBean.setImgList(weaponBean.getWeaponImgs().split(","));
        return weaponBean;
    }

    @Transactional
    public void deleteWeapon(int weaponId) throws IOException {
        // É¾³ýÍ¼Æ¬×ÊÔ´
        MicroWeaponBean weaponBean = this.findByWeaponId(weaponId);
        for (String imgPath : weaponBean.getImgList()) {
            resourceServer.deleteResource(imgPath);
        }
        dao.delete(weaponId);
    }

    private Page<MicroWeaponBean> setImgs(Page<MicroWeaponBean> pageInfo) {
        for (MicroWeaponBean weaponBean : pageInfo.getContent()) {
            weaponBean.setImgList(weaponBean.getWeaponImgs().split(","));
        }
        return pageInfo;
    }
}
