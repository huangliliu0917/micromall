package com.micromall.adminWeb.controller.delivery;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.delivery.BarCodeBean;
import com.micromall.datacenter.service.delivery.BarCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.image.BufferedImage;

/**
 * Created by Administrator on 2015/6/26.
 */
@Controller
public class BarCodeController extends BaseController {
    @Autowired
    private BarCodeService codeService;

    @RequestMapping("/delivery/barCodeList")
    public String barCodeList(int goodId, int printed,
                              @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                              Model model) {
        Page<BarCodeBean> pageInfo = codeService.findAll(getCustomerId(), goodId, printed, pageIndex, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("customerId", getCustomerId());
        return "delivery/barcode_list";
    }
}
