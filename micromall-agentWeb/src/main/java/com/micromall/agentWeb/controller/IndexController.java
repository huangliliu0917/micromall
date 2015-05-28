package com.micromall.agentWeb.controller;

import com.micromall.datacenter.service.agent.MallAgentLevelService;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import com.micromall.datacenter.service.order.MallOrderService;
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
    @Autowired
    private MallOrderService orderService;
    @Autowired
    private MallAgentLevelService levelService;

    @RequestMapping("/login")
    public String loginLoad(@RequestParam(value = "returnUrl", required = false, defaultValue = "") String returnUrl, Model model) {
        model.addAttribute("configBean", configService.findByCustomerId(getCustomerId()));
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("returnUrl", returnUrl);
        return "login";
    }

    @RequestMapping({"/index", ""})
    public String indexLoad(ModelMap modelMap) {
        modelMap.put("unShipCount", orderService.findCountInOrder(getCustomerId(), getAgentId()));
        modelMap.put("customerId", getCustomerId());
        return "index";
    }

    @RequestMapping("/applyAgent")
    public String applyLoad(Model model) {
        model.addAttribute("levelList", levelService.findByCustomerId(getCustomerId()));
        model.addAttribute("customerId", getCustomerId());

        return "agent/agent_apply";
    }
}
