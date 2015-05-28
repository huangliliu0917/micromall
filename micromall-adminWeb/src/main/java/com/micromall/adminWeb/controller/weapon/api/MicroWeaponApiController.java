package com.micromall.adminWeb.controller.weapon.api;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.weapon.MicroWeaponBean;
import com.micromall.datacenter.service.weapon.MicroWeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/27.
 */
@Controller
public class MicroWeaponApiController extends BaseController {
    private Map<Object, Object> responseData = new HashMap<Object, Object>();
    @Autowired
    private MicroWeaponService weaponService;

    @RequestMapping("/weaponApi/saveWeapon")
    @ResponseBody
    public Map<Object, Object> saveWeapon(MicroWeaponBean weaponBean) {
        int result = 0;
        try {
            weaponBean.setCustomerId(getCustomerId());
            weaponService.save(weaponBean);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping("/weaponApi/deleteWeapon")
    @ResponseBody
    public Map<Object, Object> deleteWeapon(int weaponId) {
        int result = 0;
        try {
            weaponService.deleteWeapon(weaponId);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }
}
