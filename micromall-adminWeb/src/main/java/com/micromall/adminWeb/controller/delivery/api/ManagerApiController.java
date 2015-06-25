package com.micromall.adminWeb.controller.delivery.api;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.delivery.ManagerBean;
import com.micromall.datacenter.service.delivery.ManagerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/23.
 */
@Controller
@RequestMapping(value = "/managerApi", method = RequestMethod.POST)
@Scope("request")
public class ManagerApiController extends BaseController {
    @Autowired
    private ManagerService managerService;

    private Map<Object, Object> responseData = new HashMap<Object, Object>();

    /**
     * 添加管理员
     *
     * @param managerBean
     * @return
     */
    @RequestMapping("/addManager")
    @ResponseBody
    public Map<Object, Object> addManager(ManagerBean managerBean) {
        int result = 0;

        try {
            if (managerService.accountExists(managerBean.getAccount())) {
                result = 2; //账户已存在
            } else {
                managerBean.setAddTime(new Date());
                managerBean.setCustomerId(getCustomerId());
                managerBean.setPassword(managerBean.getPassword().toLowerCase());
                managerService.save(managerBean);
                result = 1;
            }
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    /**
     * 修改管理员姓名
     *
     * @param newName
     * @param managerId
     * @return
     */
    @RequestMapping("/updateName")
    @ResponseBody
    public Map<Object, Object> updateName(String newName, int managerId) {
        int result = 0;
        try {
            managerService.updateName(managerId, newName);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    /**
     * 重置密码
     *
     * @param managerId
     * @return
     */
    @RequestMapping("/resetPassword")
    @ResponseBody
    public Map<Object, Object> resetPass(int managerId, String newPass) {
        int result = 0;
        try {
            managerService.resetPassword(managerId, newPass);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    /**
     * 删除管理员
     *
     * @param managerId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<Object, Object> delete(int managerId) {
        int result = 0;
        try {
            managerService.delete(managerId);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }
}
