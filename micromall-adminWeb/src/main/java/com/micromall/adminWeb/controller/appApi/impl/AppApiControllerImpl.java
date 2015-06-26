package com.micromall.adminWeb.controller.appApi.impl;

import com.micromall.adminWeb.controller.appApi.AppApiController;
import com.micromall.datacenter.bean.delivery.ManagerBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.service.delivery.DeliverItemService;
import com.micromall.datacenter.service.delivery.ManagerService;
import com.micromall.datacenter.service.order.MallOrderService;
import com.micromall.datacenter.viewModel.appModel.IndexInfoModel;
import com.micromall.datacenter.viewModel.appModel.ManagerAppModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/24.
 */
@Controller
@RequestMapping("/app")
public class AppApiControllerImpl implements AppApiController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private MallOrderService orderService;
    @Autowired
    private DeliverItemService itemService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ManagerAppModel login(String account, String password) {
        ManagerBean managerBean = managerService.login(account, password);
        return managerService.getAppModel(managerBean);
    }

    @RequestMapping(value = "/orderList", method = RequestMethod.GET)
    @ResponseBody
    public List<MallOrderBean> orderList(int customerId, @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey,
                                         @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                         @RequestParam(value = "deliverStatus", required = false, defaultValue = "-1") int deliverStatus) {
        List<MallOrderBean> list = orderService.findOrderList(customerId, searchKey, pageIndex, deliverStatus, pageIndex, 10).getContent();
        return list;
    }

    @RequestMapping(value = "/getIndexInfo", method = RequestMethod.GET)
    @ResponseBody
    public IndexInfoModel getIndexInfo(int customerId) {
        IndexInfoModel indexInfoModel = new IndexInfoModel();
        indexInfoModel.setDeliveredNum(orderService.countByDeliverStatus(customerId, 1));
        indexInfoModel.setUndeliveredNum(orderService.countByDeliverStatus(customerId, 0));
        indexInfoModel.setTodayOrderNum(orderService.countByTodayOrders(customerId));
        return indexInfoModel;
    }

    @RequestMapping(value = "/getOrderDetail", method = RequestMethod.GET)
    @ResponseBody
    public MallOrderBean getOrderDetail(String orderId) {
        return orderService.findByOrderId(orderId);
    }

    @RequestMapping(value = "deliverPro", method = RequestMethod.POST)
    @ResponseBody
    public int deliverPro(String orderId, String proCodes, int customerId, int managerId,
                          @RequestParam(value = "logiName", required = false, defaultValue = "") String logiName,
                          @RequestParam(value = "logiNum", required = false, defaultValue = "") String logiNum) {
        int result = 0;
        try {
            itemService.deliverPro(customerId, proCodes, orderId, logiName, logiNum, managerId);
            result = 1;
        } catch (Exception e) {

        }
        return result;
    }

    @RequestMapping(value = "/updatePass", method = RequestMethod.POST)
    @ResponseBody
    public int updatePass(int managerId, String oldPass, String newPass) {
        int result = 0;
        try {
            managerService.updatePassword(managerId, oldPass, newPass);
            result = 1;
        } catch (Exception e) {

        }
        return result;
    }
}
