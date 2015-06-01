package com.micromall.agentWeb.controller.weapon;

import com.micromall.agentWeb.controller.BaseController;
import com.micromall.datacenter.bean.weapon.MicroWeaponBean;
import com.micromall.datacenter.service.weapon.MicroWeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2015/5/28.
 */
@Controller
public class MicroWeaponController extends BaseController {
    @Autowired
    private MicroWeaponService weaponService;

    @RequestMapping("/weapon/weaponList")
    public String weaponList(@RequestParam(value = "weaponType", required = false, defaultValue = "0") int weaponType,
                             @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex, Model model) {
        int pageSize = weaponType == 0 ? 5 : 10;
        Page<MicroWeaponBean> pageInfo = weaponService.findByCustomerIdAndWeaponType(getCustomerId(), weaponType, pageIndex, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("weaponType", weaponType);
        model.addAttribute("customerId", getCustomerId());

        return "weapon/weapon_list";
    }

    @RequestMapping("/weapon/weaponDetail")
    public String weaponDetail(int weaponId, Model model) {
        MicroWeaponBean weaponBean = weaponService.findByWeaponId(weaponId);
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("weaponBean", weaponBean);
        return "weapon/weapon_detail";
    }
}
