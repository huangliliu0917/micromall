package com.micromall.adminWeb.controller.agent;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallUserBean;
import com.micromall.datacenter.service.agent.MallUserService;
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
public class MallUserController extends BaseController {
    @Autowired
    private MallUserService userService;

    @RequestMapping("/user/userList")
    public String userList(int agentId, @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey,
                           @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex, Model model) {
        Page<MallUserBean> pageInfo = userService.findAll(getCustomerId(), pageIndex, pageSize, agentId, searchKey);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("searchKey", searchKey);
        model.addAttribute("agentId", agentId);

        return "agent/user_list";
    }
}
