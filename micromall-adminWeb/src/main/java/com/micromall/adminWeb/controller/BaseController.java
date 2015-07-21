package com.micromall.adminWeb.controller;

import com.micromall.datacenter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/5/16.
 */
@Controller
@Scope("request")
public class BaseController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    protected int pageSize = 20;

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

    protected int getCustomerId() {
        return Integer.parseInt(request.getSession().getAttribute("customerId").toString());
    }

    protected void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
