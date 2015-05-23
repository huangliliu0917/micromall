package com.micromall.adminWeb.controller.agent;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/18.
 */
@Controller
public class AgentLevelController extends BaseController {
    @Autowired
    private MallAgentLevelService levelService;

    @RequestMapping("/agent/levelList")
    public ModelAndView levelList() {
        ModelMap modelMap = new ModelMap();
        List<MallAgentLevelBean> list = levelService.findByCustomerId(getCustomerId());
        modelMap.put("levelList", list);

        return new ModelAndView("agent/level_list", modelMap);
    }
}
