package com.micromall.agentWeb.controller;

import com.micromall.agentWeb.bean.CookieHelper;
import com.micromall.datacenter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/5/16.
 */
@Controller
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    protected int pageSize = 10;

    protected String getQueryString(String queryKey, String def) {
        String value = request.getParameter(queryKey);
        if (StringUtil.isEmptyStr(value)) {
            return def;
        }
        return value;
    }

    protected int getQueryString(String queryKey, int def) {
        String value = request.getParameter(queryKey);
        if (StringUtil.isEmptyStr(value)) {
            return def;
        }
        return Integer.parseInt(value);
    }

    private int customerId;
    private int agentId;

    public int getAgentId() {
        //return CookieHelper.getCookieValInteger(request, "agentId_" + getCustomerId());
        Object agentIdObj = request.getSession().getAttribute("agentId_" + getCustomerId());
        if (agentIdObj == null) {
            return 0;
        } else {
            return Integer.parseInt(agentIdObj.toString());
        }
    }

    protected int getCustomerId() {
        return getQueryString("customerId", 0);
    }

}
