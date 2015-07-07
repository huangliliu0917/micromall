package com.micromall.adminWeb.controller.order.api;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.delivery.DeliverItemBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.service.delivery.DeliverItemService;
import com.micromall.datacenter.service.order.MallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/25.
 */
@Controller
@Scope("request")
public class OrderApiController extends BaseController {
    private Map<Object, Object> responseData = new HashMap<Object, Object>();
    @Autowired
    private MallOrderService orderService;
    @Autowired
    private DeliverItemService deliverItemService;

    /**
     * 确认发货
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/orderApi/confirmShip")
    @ResponseBody
    public Map<Object, Object> confirmShip(String orderId, String logiName, String logiNum) {
        int result = 0;
        try {
            MallOrderBean orderBean = orderService.findByOrderId(orderId);
            DeliverItemBean deliverItemBean = deliverItemService.findByOrderId(orderId);
            orderService.confirmShip(orderBean, deliverItemBean.getProList(), logiName, logiNum);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping("/orderApi/reDeliver")
    @ResponseBody
    public Map<Object, Object> reDeliver(String orderId, int itemId) {
        Map<Object, Object> responseData = new HashMap<Object, Object>();
        int result = 0;
        try {
            orderService.updateDeliver(orderId, 0);
            deliverItemService.setInvalidate(itemId);
            result = 1;
        } catch (Exception e) {

        }
        responseData.put("result", result);
        return responseData;
    }
}
