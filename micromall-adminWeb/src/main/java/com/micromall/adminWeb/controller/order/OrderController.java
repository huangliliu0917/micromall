package com.micromall.adminWeb.controller.order;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.service.order.MallOrderService;
import com.micromall.datacenter.viewModel.order.MallOrderSearchViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2015/5/25.
 */
@Controller
public class OrderController extends BaseController {
    @Autowired
    private MallOrderService orderService;

    @RequestMapping("/order/orderList")
    public String orderList(MallOrderSearchViewModel searchViewModel,
                            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                            @RequestParam(value = "orderStatus", required = false, defaultValue = "-1") int orderStatus, Model model) {
        searchViewModel.setOrderStatus(orderStatus);
        Page<MallOrderBean> pageInfo = orderService.findAll(searchViewModel, pageIndex, pageSize, getCustomerId());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("searchViewModel", searchViewModel);
        model.addAttribute("pageIndex", pageIndex);
        return "order/order_list";
    }

    @RequestMapping("/order/orderDetail")
    public String orderDetail(String orderId, Model model) {
        MallOrderBean orderBean = orderService.findByOrderId(orderId);
        model.addAttribute("orderBean", orderBean);
        return "order/order_detail";
    }
}
