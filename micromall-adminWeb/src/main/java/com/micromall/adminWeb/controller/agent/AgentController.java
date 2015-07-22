package com.micromall.adminWeb.controller.agent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallAgentApplyBean;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.bean.agent.MallGroupBean;
import com.micromall.datacenter.service.agent.MallAgentApplyService;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.agent.MallGroupService;
import com.micromall.datacenter.viewModel.agent.MallAgentSearchViewModel;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2015/5/15.
 */
@Controller
public class AgentController extends BaseController {

    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallAgentLevelService levelService;
    @Autowired
    private MallAgentApplyService applyService;
    @Autowired
    private MallGroupService groupService;

    @RequestMapping("/agent/agentList")
    public ModelAndView agentList(MallAgentSearchViewModel searchParams,
                                  @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                  @RequestParam(value = "superAgentId", required = false, defaultValue = "0") int superAgentId,
                                  @RequestParam(value = "agentStatus", required = false, defaultValue = "-1") int agentStatus) throws JsonProcessingException {
        ModelMap modelMap = new ModelMap();
        searchParams.setAgentStatus(agentStatus);
        searchParams.setCustomerId(getCustomerId());
        Page<MallAgentBean> pageInfo = agentService.findAll(searchParams, pageIndex, pageSize, superAgentId);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("pageIndex", pageIndex);
        modelMap.put("superAgentId", superAgentId);
        modelMap.put("searchParams", searchParams);
        modelMap.put("agentStatus", agentStatus);
        pageInfo.getTotalElements();

        List<MallAgentLevelBean> levelList = levelService.findByCustomerId(getCustomerId());
        modelMap.put("levelList", levelList);
        List<MallGroupBean> groupList = groupService.findAll(getCustomerId());
        ObjectMapper objectMapper = new ObjectMapper();
        modelMap.put("groupListJson", objectMapper.writeValueAsString(groupList));
        modelMap.put("groupList", groupList);

        return new ModelAndView("agent/agent_list", modelMap);
    }

    @RequestMapping("/agent/agentEdit")
    public ModelAndView editAgent(@RequestParam(value = "agentId", required = false, defaultValue = "0") int agentId) {
//        int agentId = getQueryString("agentId", 0);
        ModelMap modelMap = new ModelMap();
        modelMap.put("agentId", agentId);
        modelMap.put("groupList", groupService.findAll(getCustomerId()));
        if (agentId > 0) {
            MallAgentBean agentBean = agentService.findByAgentId(agentId);
            if (agentBean.getSuperAgentId() > 0) {
                MallAgentBean superAgent = agentService.findByAgentId(agentBean.getSuperAgentId());
                modelMap.put("superAgent", superAgent);
            }
            modelMap.put("agentBean", agentBean);
        }
        List<MallAgentLevelBean> levelList = levelService.findByCustomerId(getCustomerId());
        modelMap.put("levelList", levelList);
        return new ModelAndView("agent/agent_edit", modelMap);
    }

    @RequestMapping("/agent/applyAgentList")
    public String applyAgentList(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                 @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey,
                                 @RequestParam(value = "applyStatus", required = false, defaultValue = "-1") int applyStatus, Model model) {
        Page<MallAgentApplyBean> pageInfo = applyService.findByCustomerId(getCustomerId(), searchKey, applyStatus, pageIndex, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("applyStatus", applyStatus);
        model.addAttribute("searchKey", searchKey);
        return "agent/agent_apply_list";
    }

    @RequestMapping("/agent/applyDetail")
    public String applyDetail(int applyId, Model model) {
        MallAgentApplyBean applyBean = applyService.findByApplyId(applyId);
        model.addAttribute("applyId", applyId);
        model.addAttribute("applyBean", applyBean);
        model.addAttribute("levelList", levelService.findByCustomerId(getCustomerId()));
        model.addAttribute("applyLevel", levelService.findByLevelId(applyBean.getApplyLevelId()));
        model.addAttribute("groupList", groupService.findAll(getCustomerId()));
        return "agent/agent_apply_detail";
    }
}
