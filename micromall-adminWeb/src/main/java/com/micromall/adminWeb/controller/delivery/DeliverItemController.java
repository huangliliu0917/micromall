package com.micromall.adminWeb.controller.delivery;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.delivery.DeliverItemBean;
import com.micromall.datacenter.service.delivery.DeliverItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2015/6/24.
 */
@Controller
public class DeliverItemController extends BaseController {
    @Autowired
    private DeliverItemService deliverItemService;

    @RequestMapping("/delivery/deliverList")
    public String deliverList(@RequestParam(value = "managerKey", required = false, defaultValue = "") String managerKey,
                              @RequestParam(value = "shipKey", required = false, defaultValue = "") String shipKey,
                              @RequestParam(value = "beginTime", required = false, defaultValue = "") String beginTime,
                              @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime,
                              @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                              Model model) {
        Page<DeliverItemBean> pageInfo = deliverItemService.findAll(getCustomerId(), managerKey, shipKey, beginTime, endTime, pageIndex, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("managerKey", managerKey);
        model.addAttribute("shipKey", shipKey);
        model.addAttribute("beginTime", beginTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("pageIndex", pageIndex);

        return "delivery/deliver_list";
    }
}
