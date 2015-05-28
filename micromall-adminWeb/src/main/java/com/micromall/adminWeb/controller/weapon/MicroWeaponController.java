package com.micromall.adminWeb.controller.weapon;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.weapon.MicroWeaponBean;
import com.micromall.datacenter.service.weapon.MicroWeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2015/5/27.
 */
@Controller
public class MicroWeaponController extends BaseController {
    @Autowired
    private MicroWeaponService weaponService;

    @RequestMapping("/weapon/weaponList")
    public String weaponList(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                             @RequestParam(value = "weaponType", required = false, defaultValue = "0") int weaponType, Model model) {
        int pageSize = weaponType == 0 ? 4 : 10;
        Page<MicroWeaponBean> pageInfo = weaponService.findByCustomerIdAndWeaponType(getCustomerId(), weaponType, pageIndex, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("weaponType", weaponType);

        return "weapon/weapon_list";
    }

    @RequestMapping("/weapon/weaponEdit")
    public String weaponEdit(@RequestParam(value = "weaponId", required = false, defaultValue = "0") int weaponId,
                             @RequestParam(value = "weaponType", required = false, defaultValue = "0") int weaponType, Model model) {
        if (weaponId > 0) {
            model.addAttribute("weaponBean", weaponService.findByWeaponId(weaponId));
        }
        model.addAttribute("weaponType", weaponType);
        model.addAttribute("weaponId", weaponId);
        if (weaponType == 0) {
            return "weapon/weapon_edit_imgs";
        } else {
            return "weapon/weapon_edit_img";
        }
    }
}
