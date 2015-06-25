package com.micromall.adminWeb.controller.appApi.impl;

import com.micromall.datacenter.service.delivery.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Administrator on 2015/6/24.
 */
@Controller
@RequestMapping("/app")
public class ManagerAppApiImpl {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/test")
    @ResponseBody
    public int test() {
        return 1;
    }
}
