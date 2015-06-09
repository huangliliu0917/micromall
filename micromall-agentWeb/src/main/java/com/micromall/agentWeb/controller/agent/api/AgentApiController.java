package com.micromall.agentWeb.controller.agent.api;

import com.micromall.agentWeb.bean.CookieHelper;
import com.micromall.agentWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallAgentApplyBean;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallUserBean;
import com.micromall.datacenter.service.agent.MallAgentApplyService;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.agent.MallUserService;
import com.micromall.datacenter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Administrator on 2015/5/22.
 */
@Controller
@Scope("request")
public class AgentApiController extends BaseController {
    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallUserService userService;
    @Autowired
    private MallAgentApplyService applyService;

    private Map<Object, Object> responseData = new HashMap<Object, Object>();

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> checkLogin(String account, String password) {
        int result = 0;
        try {
            if (!StringUtil.isEmpty(account) && !StringUtil.isEmpty(password)) {
                MallAgentBean agentBean = agentService.checkLogin(account, password, getCustomerId());
                if (agentBean != null) {
                    //登录成功
                    CookieHelper.setCookie(response, "account_" + getCustomerId(), account);
                    CookieHelper.setCookie(response, "password_" + getCustomerId(), password);
                    CookieHelper.setCookie(response, "agentId_" + getCustomerId(), String.valueOf(agentBean.getAgentId()));
                    result = 1;
                }
            }
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> logOut() {
        int result = 0;
        try {
            CookieHelper.removeCookie(response, "account_" + getCustomerId());
            CookieHelper.removeCookie(response, "password_" + getCustomerId());
            request.getSession().setAttribute("loginToken_" + getCustomerId(), null);
            request.getSession().setAttribute("agentId_" + getCustomerId(), null);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping(value = "/agentApi/saveAgent", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> saveAgent(MallAgentBean agentBean, int levelId) {
        int result = 0;
        try {
            if (agentBean.getAgentId() == 0 && agentService.accountExist(agentBean.getAgentAccount(), getCustomerId())) {
                result = 2; //账号已存在
            } else {
                if (agentBean.getAgentId() > 0) {
                    MallAgentBean preBean = agentService.findByAgentId(agentBean.getAgentId());
                    preBean.setName(agentBean.getName());
                    preBean.setAgentArea(agentBean.getAgentArea());
                    preBean.setAgentChannel(agentBean.getAgentChannel());
                    preBean.setAgentCardId(agentBean.getAgentCardId());
                    preBean.setAgentQQ(agentBean.getAgentQQ());
                    preBean.setAgentWeixin(agentBean.getAgentWeixin());
                    preBean.setAgentAddr(agentBean.getAgentAddr());
                    if (StringUtil.isNotEmpty(agentBean.getAgentPassword())) {
                        preBean.setAgentPassword(agentBean.getAgentPassword());
                    }
                    levelId = preBean.getAgentLevel().getLevelId();
                    agentBean = preBean;
                } else {
                    agentBean.setCustomerId(getCustomerId());
                    agentBean.setAddTime(new Date());
                    agentBean.setAgentStatus(0);
                }
                agentBean.setSuperAgentId(getAgentId());

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
    public Map<Object, Object> setDelete(int agentId) {
        int result = 0;
        try {
            agentService.setDelete(1, agentId);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }

        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping(value = "/agentApi/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> deleteUser(int userId) {
        int result = 0;
        try {
            userService.delete(userId);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }

        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping(value = "/agentApi/saveUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> saveUser(MallUserBean userBean) {
        int result = 0;
        try {
            if (userService.userExists(getCustomerId(), userBean.getUserMobile(), getAgentId(), userBean.getUserId()) == 0) {
                if (userBean.getUserId() > 0) {
                    userBean.setCustomerId(getCustomerId());
                    userBean.setIsDelete(0);
                }
                userService.save(userBean, getAgentId());
                result = 1;
            } else {
                result = 2;
            }
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping(value = "/agentApi/agentApply", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> applyAgent(MallAgentApplyBean applyBean) {
        int result = 0;
        try {
            applyBean.setApplyTime(new Date());
            applyBean.setApplyStatus(0);
            applyService.save(applyBean);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping(value = "/agentApi/updatePass", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> updatePass(String newPass, String orignalPass) {
        int result = 0;
        try {
            MallAgentBean agentBean = agentService.findByAgentId(getAgentId());

            //重新登录
            agentService.updatePassword(newPass, getAgentId(), orignalPass);
            CookieHelper.setCookie(response, "account_" + getCustomerId(), agentBean.getAgentAccount());
            CookieHelper.setCookie(response, "password_" + getCustomerId(), newPass);
            request.getSession().setAttribute("loginToken_" + getCustomerId(), agentBean.getAgentAccount());
            request.getSession().setAttribute("agentId_" + getCustomerId(), agentBean.getAgentId());
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping("/agentApi/updateAddr")
    @ResponseBody
    public Map<Object, Object> updateAddr(String newAddr) {
        int result = 0;
        try {
            agentService.updateAddr(newAddr, getAgentId());
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }
}
