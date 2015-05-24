package com.micromall.agentWeb.controller.order.api;

import com.micromall.agentWeb.controller.BaseController;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.bean.orders.MallOrderItemBean;
import com.micromall.datacenter.service.order.MallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/24.
 */
@Controller
@Scope("request")
public class OrderApiController extends BaseController {
    private Map<Object, Object> responseData = new HashMap<Object, Object>();
    @Autowired
    private MallOrderService orderService;

    @RequestMapping("/orderApi/createOrder")
    @ResponseBody
    public Map<Object, Object> createOrder(MallOrderBean orderBean, int goodId) {
        int result = 0;
        try {
            orderBean.setAddTime(new Date());
            orderBean.setCustomerId(getCustomerId());
            orderBean.setDeliverPath("|" + getAgentId());
            orderBean.setOrderStatus(0);
            orderBean.setOwnerId(getAgentId());
            //orderBean.setRealShipId(getAgentId());

            orderService.create(orderBean, goodId);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping("/orderApi/confirmShip")
    @ResponseBody
    public Map<Object, Object> confirmShip(String orderId, String proCodes, String shipInfo) {
        int result = 0;
        try {
            MallOrderBean orderBean = orderService.findByOrderId(orderId);
            orderBean.setOrderStatus(1);
            orderBean.setRealShipId(getAgentId());
            orderService.confirmShip(orderBean, proCodes.split(","), shipInfo);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }
}
