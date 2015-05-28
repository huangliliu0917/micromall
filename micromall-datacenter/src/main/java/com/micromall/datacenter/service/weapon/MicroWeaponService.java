package com.micromall.datacenter.service.weapon;

import com.micromall.datacenter.bean.weapon.MicroWeaponBean;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2015/5/27.
 */
public interface MicroWeaponService {
    MicroWeaponBean save(MicroWeaponBean weaponBean);

    Page<MicroWeaponBean> findByCustomerIdAndWeaponType(int customerId, int weaponType, int pageIndex, int pageSize);

    MicroWeaponBean findByWeaponId(int weaponId);

    void deleteWeapon(int weaponId);
}
