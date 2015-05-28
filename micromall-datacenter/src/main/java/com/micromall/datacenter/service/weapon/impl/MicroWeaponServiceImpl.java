package com.micromall.datacenter.service.weapon.impl;

import com.micromall.datacenter.bean.weapon.MicroWeaponBean;
import com.micromall.datacenter.dao.weapon.MicroWeaponDao;
import com.micromall.datacenter.service.weapon.MicroWeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/5/27.
 */
@Service
public class MicroWeaponServiceImpl implements MicroWeaponService {
    @Autowired
    private MicroWeaponDao dao;

    public MicroWeaponBean save(MicroWeaponBean weaponBean) {
        return dao.save(weaponBean);
    }

    public Page<MicroWeaponBean> findByCustomerIdAndWeaponType(int customerId, int weaponType, int pageIndex, int pageSize) {
        Page<MicroWeaponBean> pageInfo = dao.findByCustomerIdAndWeaponType(customerId, weaponType, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "weaponId")));
        return setImgs(pageInfo);
    }

    public MicroWeaponBean findByWeaponId(int weaponId) {
        MicroWeaponBean weaponBean = dao.findOne(weaponId);
        weaponBean.setImgList(weaponBean.getWeaponImgs().split(","));
        return weaponBean;
    }

    public void deleteWeapon(int weaponId) {
        dao.delete(weaponId);
    }

    private Page<MicroWeaponBean> setImgs(Page<MicroWeaponBean> pageInfo) {
        for (MicroWeaponBean weaponBean : pageInfo.getContent()) {
            weaponBean.setImgList(weaponBean.getWeaponImgs().split(","));
        }
        return pageInfo;
    }
}
