package com.micromall.adminWeb.controller.delivery;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.delivery.BatchBarCodeBean;
import com.micromall.datacenter.bean.delivery.MainBarCodeBean;
import com.micromall.datacenter.bean.delivery.SubBarCodeBean;
import com.micromall.datacenter.service.delivery.BarCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2015/6/26.
 */
@Controller
@RequestMapping("/delivery")
public class BarCodeController extends BaseController {
    @Autowired
    private BarCodeService codeService;

    @RequestMapping("/batchCodeList")
    public String batchBarCodeList(int goodId, String goodName,
                                   @RequestParam(value = "printed", required = false, defaultValue = "0") int printed,
                                   @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                   Model model) {
        Page<BatchBarCodeBean> pageInfo = codeService.findBatchBarCodeAll(getCustomerId(), goodId, printed, pageIndex, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("goodName", goodName);
        model.addAttribute("goodId", goodId);
        model.addAttribute("printed", printed);

        return "delivery/batchcode_list";
    }

    @RequestMapping("/barCodeList")
    public String barCodeList(long batchCodeId, String goodName, int goodId,
                              @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                              Model model) {
        Page<MainBarCodeBean> pageInfo = codeService.findMainBarCodeAll(batchCodeId, pageIndex, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("goodName", goodName);
        model.addAttribute("goodId", goodId);
        return "delivery/mainbarcode_list";
    }

    @RequestMapping("/subBarCodeList")
    public String subBarCode(long mainCodeId, Model model) {
        List<SubBarCodeBean> subBarCodeBeans = codeService.findSubBarCodeByMainCode(mainCodeId);
        model.addAttribute("mainCodeId", mainCodeId);
        model.addAttribute("subBarCodeBeans", subBarCodeBeans);
        return "delivery/subbarcode_list";
    }

    @RequestMapping("/printBarCode")
    public String printBarCode(long batchCodeId, Model model) {
        model.addAttribute("list", codeService.findMainBarCodeAll(batchCodeId));
        return "delivery/printbarcode";
    }
}
