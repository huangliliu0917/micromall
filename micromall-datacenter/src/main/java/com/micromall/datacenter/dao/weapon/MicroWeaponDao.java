package com.micromall.datacenter.dao.weapon;

import com.micromall.datacenter.bean.weapon.MicroWeaponBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2015/5/27.
 */
public interface MicroWeaponDao extends JpaRepository<MicroWeaponBean, Integer> {
    Page<MicroWeaponBean> findByCustomerIdAndWeaponType(int customerId, int weaponType, Pageable pageable);


}
