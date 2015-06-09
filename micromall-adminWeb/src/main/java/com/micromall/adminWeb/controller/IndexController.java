package com.micromall.adminWeb.controller;

import com.micromall.adminWeb.bean.CookieHelper;
import com.micromall.datacenter.bean.config.MallBaseConfigBean;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Administrator on 2015/5/21.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private MallBaseConfigService configService;

    @RequestMapping({"", "index"})
    public ModelAndView pageLoad() {
        ModelMap modelMap = new ModelMap();
        MallBaseConfigBean configBean = configService.findByCustomerId(getCustomerId());
        modelMap.put("configBean", configBean);
        return new ModelAndView("index", modelMap);
    }

    @RequestMapping("/micromall/test/admintest")
    public String testIn(Model model) throws IOException {

        getResponse().sendRedirect("/index");
        return null;
    }
}
