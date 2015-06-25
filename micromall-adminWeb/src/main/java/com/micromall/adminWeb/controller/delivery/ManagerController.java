package com.micromall.adminWeb.controller.delivery;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.delivery.ManagerBean;
import com.micromall.datacenter.service.delivery.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2015/6/23.
 */
@Controller
@RequestMapping("/delivery")
public class ManagerController extends BaseController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/managerList")
    public String managerList(@RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey,
                              @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                              Model model) {
        Page<ManagerBean> pageInfo = managerService.findAll(getCustomerId(), searchKey, pageIndex, pageSize);
        model.addAttribute("searchKey", searchKey);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("pageInfo", pageInfo);
        return "delivery/manager_list";
    }
}
