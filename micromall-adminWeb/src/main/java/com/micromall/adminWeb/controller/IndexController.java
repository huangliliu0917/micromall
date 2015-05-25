package com.micromall.adminWeb.controller;

import com.micromall.datacenter.bean.config.MallBaseConfigBean;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/5/21.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private MallBaseConfigService configService;

    @RequestMapping({"","index"})
    public ModelAndView pageLoad() {
        ModelMap modelMap = new ModelMap();
        MallBaseConfigBean configBean = configService.findByCustomerId(getCustomerId());
        modelMap.put("configBean", configBean);
        return new ModelAndView("index", modelMap);
    }
}
