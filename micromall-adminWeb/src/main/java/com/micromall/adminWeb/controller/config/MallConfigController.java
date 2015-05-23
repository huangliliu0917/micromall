package com.micromall.adminWeb.controller.config;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.config.MallBaseConfigBean;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/5/21.
 */
@Controller
public class MallConfigController extends BaseController {
    @Autowired
    private MallBaseConfigService configService;

    @RequestMapping("/config/mallConfig")
    public ModelAndView pageLoad() {
        ModelMap modelMap = new ModelMap();
        MallBaseConfigBean configBean = configService.findByCustomerId(getCustomerId());
        modelMap.put("configBean", configBean);


        return new ModelAndView("config/mall_config", modelMap);
    }

    @RequestMapping("/config/saveConfig")
    @ResponseBody
    public int saveConfig(MallBaseConfigBean configBean) {
        int result = 0;
        try {
            configBean.setCustomerId(getCustomerId());
            configService.save(configBean);
            result = 1;
        } catch (Exception e) {

        }

        return result;
    }
}
