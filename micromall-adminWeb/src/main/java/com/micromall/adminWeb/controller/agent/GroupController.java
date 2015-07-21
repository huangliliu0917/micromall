package com.micromall.adminWeb.controller.agent;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.service.agent.MallGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by allan on 2015/7/21.
 */
@Controller
public class GroupController extends BaseController {
    @Autowired
    private MallGroupService groupService;

    @RequestMapping("/group/groupList")
    public String groupList(Model model) {
        model.addAttribute("groupList", groupService.findAll(getCustomerId()));
        return "agent/group_list";
    }
}
