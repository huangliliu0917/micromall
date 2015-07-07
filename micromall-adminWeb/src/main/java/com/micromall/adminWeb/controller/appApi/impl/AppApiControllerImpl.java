package com.micromall.adminWeb.controller.appApi.impl;

import com.micromall.adminWeb.bean.ApiResult;
import com.micromall.adminWeb.controller.appApi.AppApiController;
import com.micromall.datacenter.bean.config.MallBaseConfigBean;
import com.micromall.datacenter.bean.delivery.ManagerBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import com.micromall.datacenter.service.delivery.DeliverItemService;
import com.micromall.datacenter.service.delivery.ManagerService;
import com.micromall.datacenter.service.order.MallOrderService;
import com.micromall.datacenter.utils.ResourceServer;
import com.micromall.datacenter.viewModel.appModel.IndexInfoModel;
import com.micromall.datacenter.viewModel.appModel.ManagerAppModel;
import com.micromall.datacenter.viewModel.appModel.OrderAppModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
    @Autowired
    private MallBaseConfigService configService;
    @Autowired
    private ResourceServer resourceServer;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<ManagerAppModel> login(String account, String password) {
        ManagerBean managerBean = managerService.login(account, password);
        ApiResult<ManagerAppModel> apiResult = new ApiResult<ManagerAppModel>();
        if (managerBean == null) {
            apiResult.setResultCode(0);
            apiResult.setMsg("登录失败");
        } else {
            apiResult.setResultCode(1);
            apiResult.setMsg("登录成功");
        }
        apiResult.setResponseData(managerService.getAppModel(managerBean));
        return apiResult;
    }

    @RequestMapping(value = "/orderList", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<List<OrderAppModel>> orderList(int customerId, @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey,
                                                    @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex, int pageSize,
                                                    @RequestParam(value = "isToday", required = false, defaultValue = "0") int isToday,
                                                    @RequestParam(value = "deliverStatus", required = false, defaultValue = "-1") int deliverStatus) {
        ApiResult<List<OrderAppModel>> apiResult = new ApiResult<List<OrderAppModel>>();
        List<MallOrderBean> list = null;
        List<OrderAppModel> resultList = null;
        int result = 0;
        try {
            list = orderService.findOrderList(customerId, searchKey, deliverStatus, isToday, pageIndex, pageSize).getContent();
            resultList = new ArrayList<OrderAppModel>();
            for (MallOrderBean orderBean : list) {
                resultList.add(orderService.getAppModel(orderBean));
            }
            result = 1;
            apiResult.setMsg("请求成功");
        } catch (Exception e) {
            apiResult.setMsg("请求失败--" + e.getMessage());
        }
        apiResult.setResponseData(resultList);
        apiResult.setResultCode(result);
        return apiResult;
    }

    @RequestMapping(value = "/getIndexInfo", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<IndexInfoModel> getIndexInfo(int customerId) {
        ApiResult<IndexInfoModel> apiResult = null;
        try {
            IndexInfoModel indexInfoModel = new IndexInfoModel();
            indexInfoModel.setDeliveredNum(orderService.countByDeliverStatus(customerId, 1));
            indexInfoModel.setUndeliveredNum(orderService.countByDeliverStatus(customerId, 0));
            indexInfoModel.setTodayOrderNum(orderService.countByTodayOrders(customerId));
            MallBaseConfigBean baseConfigBean = configService.findByCustomerId(customerId);
            indexInfoModel.setCustomerName(baseConfigBean.getTitle());
            indexInfoModel.setCustomerImg(resourceServer.resourceUri(baseConfigBean.getLogo()));
            apiResult = new ApiResult<IndexInfoModel>(1, "请求成功", indexInfoModel);
        } catch (Exception e) {
            apiResult = new ApiResult<IndexInfoModel>(0, e.getMessage(), null);
        }
        return apiResult;
    }

    @RequestMapping(value = "/getOrderDetail", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<OrderAppModel> getOrderDetail(String orderId) {
        ApiResult<OrderAppModel> apiResult = null;
        try {
            MallOrderBean orderBean = orderService.findByOrderId(orderId);
            OrderAppModel appModel = orderService.getAppModel(orderBean);
            apiResult = new ApiResult<OrderAppModel>(1, "请求成功", appModel);
        } catch (Exception e) {
            apiResult = new ApiResult<OrderAppModel>(0, e.getMessage(), null);
        }
        return apiResult;
    }

    @RequestMapping(value = "/deliverPro", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Map<String, Integer>> deliverPro(String orderId, String proCodes, int customerId, int managerId,
                                                      @RequestParam(value = "logiName", required = false, defaultValue = "") String logiName,
                                                      @RequestParam(value = "logiNum", required = false, defaultValue = "") String logiNum) {
        ApiResult<Map<String, Integer>> apiResult = null;
        int result = 0;
        try {
            Map<String, Integer> map = itemService.deliverPro(customerId, proCodes, orderId, logiName, logiNum, managerId);
            if (!map.containsValue(0)) result = 1;
            apiResult = new ApiResult<Map<String, Integer>>(result, "请求成功", map);
        } catch (Exception e) {
            apiResult = new ApiResult<Map<String, Integer>>(result, e.getMessage(), null);
        }
        return apiResult;
    }

    @RequestMapping(value = "/updatePass", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<Integer> updatePass(int managerId, String oldPass, String newPass) {
        ApiResult<Integer> apiResult = null;
        try {
            managerService.updatePassword(managerId, oldPass, newPass);
            apiResult = new ApiResult<Integer>(1, "请求成功", 1);
        } catch (Exception e) {
            apiResult = new ApiResult<Integer>(0, e.getMessage(), 0);
        }
        return apiResult;
    }
}
