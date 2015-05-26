package com.micromall.agentWeb.controller;

import com.micromall.agentWeb.controller.BaseController;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2015/5/21.
 */
@Controller
public class IndexController extends BaseController {
    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallBaseConfigService configService;

    @RequestMapping("/login")
    public String loginLoad(@RequestParam(value = "returnUrl", required = false, defaultValue = "") String returnUrl, Model model) {
        model.addAttribute("configBean", configService.findByCustomerId(getCustomerId()));
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("returnUrl", returnUrl);
        return "login";
    }

    @RequestMapping({"/index", ""})
    public String indexLoad(ModelMap modelMap) {
        modelMap.put("customerId", getCustomerId());
        return "index";
    }
}
