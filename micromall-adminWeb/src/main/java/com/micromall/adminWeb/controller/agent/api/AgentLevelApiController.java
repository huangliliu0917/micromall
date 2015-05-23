package com.micromall.adminWeb.controller.agent.api;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/18.
 */

@Controller
@Scope("request")
public class AgentLevelApiController extends BaseController {
    @Autowired
    private MallAgentLevelService levelService;

    @RequestMapping(value = "/agentApi/saveLevel", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> saveAgent(MallAgentLevelBean levelBean) {
        int result = 0;
        try {
            levelService.save(levelBean);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    private Map<Object, Object> responseData = new HashMap<Object, Object>();
}
