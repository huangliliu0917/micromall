package com.micromall.agentWeb.intercepter;

import com.micromall.agentWeb.bean.CookieHelper;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/5/18.
 */
public class AccountInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private MallAgentService agentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUrl.substring(contextPath.length());
        if (requestUrl.toLowerCase().contains("login")) {
            return true;
        }

        String customerId = request.getParameter("customerId").toString();
        String account = CookieHelper.getCookieVal(request, "account_" + customerId);
        String password = CookieHelper.getCookieVal(request, "password_" + customerId);
        if (StringUtil.isEmpty(customerId) || StringUtil.isEmpty(account) || StringUtil.isEmpty(password)) {
            response.sendRedirect("/agent/login?customerId=" + customerId + "&returnUrl=" + requestUrl);
            return false;
        } else {
            MallAgentBean agentBean = agentService.checkLogin(account, password, Integer.parseInt(customerId));
            if (agentBean != null) {
                CookieHelper.setCookie(response, "account_" + customerId, account);
                CookieHelper.setCookie(response, "password_" + customerId, password);
                CookieHelper.setCookie(response, "agentId_" + customerId, String.valueOf(agentBean.getAgentId()));
                return true;
            } else {
                request.getRequestDispatcher("/login").forward(request, response);
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
