package com.micromall.adminWeb.controller.agent.api;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallAgentApplyBean;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.bean.config.MallBaseConfigBean;
import com.micromall.datacenter.service.agent.MallAgentApplyService;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import com.micromall.datacenter.utils.SMSHelper;
import com.micromall.datacenter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.management.Agent;

import java.util.*;

/**
 * Created by Administrator on 2015/5/18.
 */
@Controller
@Scope("request")
public class AgentApiController extends BaseController {
    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallAgentLevelService levelService;
    @Autowired
    private MallAgentApplyService applyService;

    @RequestMapping(value = "/agentApi/saveAgent", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> saveAgent(MallAgentBean agentBean, @RequestParam(value = "levelId", required = false, defaultValue = "0") int levelId) {
        int result = 0;
        try {
            if (agentBean.getAgentId() == 0 && agentService.accountExist(agentBean.getAgentAccount(), getCustomerId()) && agentBean.getAgentId() == 0) {
                result = 2; //账号已存在
            } else {
                if (agentBean.getAgentId() > 0) {
                    MallAgentBean preBean = agentService.findByAgentId(agentBean.getAgentId());
                    preBean.setName(agentBean.getName());
                    preBean.setSuperAgentId(agentBean.getSuperAgentId());
                    preBean.setAgentArea(agentBean.getAgentArea());
                    preBean.setAgentChannel(agentBean.getAgentChannel());
                    preBean.setAgentCardId(agentBean.getAgentCardId());
                    preBean.setAgentQQ(agentBean.getAgentQQ());
                    preBean.setAgentWeixin(agentBean.getAgentWeixin());
                    preBean.setAgentAddr(agentBean.getAgentAddr());
                    preBean.setAgentCardImg(agentBean.getAgentCardImg());
                    preBean.setAgentTaobaoId(agentBean.getAgentTaobaoId());

                    if (StringUtil.isNotEmpty(agentBean.getAgentPassword())) {
                        preBean.setAgentPassword(agentBean.getAgentPassword());
                    }
                    agentBean = preBean;
                } else {
                    agentBean.setCustomerId(getCustomerId());
                    agentBean.setAddTime(new Date());
                    agentBean.setAgentStatus(1);
                }

                agentService.save(agentBean, levelId);
                result = 1;
            }
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }

        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping(value = "/agentApi/setDelete", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> setDelete(int agentId, int isDelete) {
        int result = 0;
        try {
            agentService.setDelete(isDelete, agentId);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }

        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping(value = "/agentApi/setAgentStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> setAgentStatus(int agentId, int status, @RequestParam(value = "refuseReason", required = false, defaultValue = "") String refuseReason) {
        int result = 0;
        try {
            agentService.updateAgentStatus(status, refuseReason, agentId);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }

        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping(value = "/agentApi/getSuperAgent", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> getSuperAgent(int levelId) {
        List<MallAgentBean> list = new ArrayList<MallAgentBean>();
        if (levelId > 0) {
            MallAgentLevelBean currentLevelBean = levelService.findByLevelId(levelId);
            int sortNum = currentLevelBean.getSortNum() - 1;
            if (sortNum >= 0) {
                MallAgentLevelBean levelBean = levelService.findByCustomerIdAndSortNum(getCustomerId(), sortNum);
                list = agentService.findAgentByAgentLevel(levelBean, getCustomerId());
            }
        }
        responseData.put("list", list);
        return responseData;
    }

    @RequestMapping(value = "/agentApi/accountExists", method = RequestMethod.POST)
    @ResponseBody
    public int accountExists(String account) {
        return agentService.accountExist(account, getCustomerId()) ? 1 : 0;
    }

    @RequestMapping(value = "/agentApi/applyAgent", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> applyAgent(String mobile, int applyId, int levelId, int superAgentId, String password, int applyStatus, String refuseReason) {
        int result = 0;
        try {
            if (agentService.accountExist(mobile, getCustomerId())) {
                result = 2;//手机号码已存在
            } else {
                MallAgentApplyBean applyBean = applyService.findByApplyId(applyId);
                applyService.updateApplyStataus(applyId, superAgentId, password, levelId, applyStatus, refuseReason);
                result = 1;
            }
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    private Map<Object, Object> responseData = new HashMap<Object, Object>();
}
