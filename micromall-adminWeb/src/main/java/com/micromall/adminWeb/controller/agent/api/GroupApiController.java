package com.micromall.adminWeb.controller.agent.api;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallGroupBean;
import com.micromall.datacenter.service.agent.MallGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by allan on 2015/7/21.
 */
@Controller
@Scope("request")
@RequestMapping("/groupApi")
public class GroupApiController extends BaseController {
    @Autowired
    private MallGroupService groupService;

    @RequestMapping("/save")
    @ResponseBody
    public int save(MallGroupBean groupBean) {
        int result = 0;
        try {
            groupBean.setCustomerId(getCustomerId());
            groupService.save(groupBean);
            result = 1;
        } catch (Exception e) {

        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int delete(int groupId) {
        int result = 0;
        try {
            if (groupService.deletable(groupId)) {
                groupService.delete(groupId);
                result = 1;
            } else {
                result = 2;
            }
        } catch (Exception e) {

        }
        return result;
    }
}
