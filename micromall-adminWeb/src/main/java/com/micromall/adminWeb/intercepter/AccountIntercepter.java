package com.micromall.adminWeb.intercepter;

import com.micromall.adminWeb.bean.CookieHelper;
import com.micromall.datacenter.utils.StringUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/5/18.
 */
public class AccountIntercepter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //to do check account
        String customerIdStr = CookieHelper.getCookieVal(request, "UserID");
        int customerId = 5;
        if (StringUtil.isNotEmpty(customerIdStr)) {
            customerId = Integer.parseInt(customerIdStr);
            request.getSession().setAttribute("customerId", customerId);
            return true;
        } else {
            request.getSession().setAttribute("customerId", customerId);
            return true;
//            response.sendRedirect("http://login.huobanplus.com");
//
//            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
