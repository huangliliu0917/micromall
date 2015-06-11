package com.micromall.agentWeb.controller.agent;

import com.micromall.agentWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2015/5/22.
 */
@Controller
public class AgentController extends BaseController {
    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallAgentLevelService levelService;
    @Autowired
    private MallBaseConfigService configService;

    @RequestMapping("/agentList")
    public String agentList(@RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey,
                            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                            @RequestParam(value = "superAgentId", required = false, defaultValue = "-1") int superAgentId, Model model) {
        if (superAgentId == -1) {
            superAgentId = getAgentId();
        }
        Page<MallAgentBean> pageInfo = agentService.findBySearchKey(getCustomerId(), searchKey, pageIndex, pageSize, superAgentId);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("superAgentId", superAgentId);
        model.addAttribute("searchKey", searchKey);
        model.addAttribute("currentAgent", getAgentId());

        return "agent/agent_list";
    }

    @RequestMapping("/agentEdit")
    public String agentEdit(@RequestParam(value = "editAgentId", required = false, defaultValue = "0") int editAgentId, Model model) {
        if (editAgentId > 0) {
            model.addAttribute("agentBean", agentService.findByAgentId(editAgentId));
        }
        MallAgentBean currentAgent = agentService.findByAgentId(getAgentId());
        List<MallAgentLevelBean> levelList = levelService.findByCustomerIdAndSortNumGreaterThan(getCustomerId(), currentAgent.getAgentLevel().getSortNum() + 1);

        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("editAgentId", editAgentId);
        model.addAttribute("levelList", levelList);

        return "agent/agent_edit";
    }

    @RequestMapping("/certificates")
    public String certificates(String agentAccount, Model model) {
        MallAgentBean agentBean = agentService.findByAgentAccountAndCustomerId(agentAccount, getCustomerId());
        model.addAttribute("agentBean", agentBean);
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("configBean", configService.findByCustomerId(getCustomerId()));

        return "certificates";
    }

    @RequestMapping("/personalSetting")
    public String personalSetting(Model model) {
        MallAgentBean preBean = agentService.findByAgentId(getAgentId());
        model.addAttribute("agentBean", preBean);
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("editAgentId", getAgentId());
        return "agent/personal_setting";
    }

    @RequestMapping("/usercenter")
    public String userCenter(Model model) {
        MallAgentBean agentBean = agentService.findByAgentId(getAgentId());
        MallAgentBean superAgent = agentService.findByAgentId(agentBean.getSuperAgentId());
        int underAgentNum = agentService.getUnderAgentNum(getAgentId());
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("agentBean", agentBean);
        model.addAttribute("superAgent", superAgent);
        model.addAttribute("underAgentNum", underAgentNum);
        return "agent/user_center";
    }
}
