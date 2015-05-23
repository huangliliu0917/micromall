package com.micromall.agentWeb.controller.agent;

import com.micromall.agentWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallUserBean;
import com.micromall.datacenter.service.agent.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2015/5/22.
 */
@Controller
public class UserController extends BaseController {
    @Autowired
    private MallUserService userService;

    @RequestMapping("/userList")
    public String userList(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                           @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey, Model model) {
        Page<MallUserBean> pageInfo = userService.findAll(getCustomerId(), pageIndex, pageSize, getAgentId(), searchKey);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("searchKey", searchKey);
        return "agent/user_list";
    }

    @RequestMapping("/userEdit")
    public String userEdit(@RequestParam(value = "userId", required = false, defaultValue = "0") int userId, Model model) {
        if (userId > 0) {
            model.addAttribute("userBean", userService.findByUserId(userId));
        }
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("userId", userId);

        return "agent/user_edit";
    }
}
