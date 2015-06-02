package com.micromall.adminWeb.controller.order.api;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.orders.MallDeliverItemBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.pdaBean.SnInfoBean;
import com.micromall.datacenter.pdaService.SnInfoService;
import com.micromall.datacenter.service.order.MallOrderService;
import com.micromall.datacenter.service.order.MallDeliverItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
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
    private SnInfoService infoService;
    @Autowired
    private MallDeliverItemService deliverSnInfoService;

    /**
     * 确认发货
     *
     * @param orderId
     * @param proCodes
     * @param shipInfo
     * @return
     */
    @RequestMapping("/orderApi/confirmShip")
    @ResponseBody
    public Map<Object, Object> confirmShip(String orderId, String proCodes, String logiName, String logiNum) {
        int result = 0;
        try {
            MallOrderBean orderBean = orderService.findByOrderId(orderId);
            orderService.confirmShip(orderBean, proCodes.split(","), logiName, logiNum);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping("/orderApi/getShipProList")
    @ResponseBody
    public Map<Object, Object> getShipProList(String agentMobile) {
        List<MallDeliverItemBean> snList = deliverSnInfoService.findAll(getCustomerId(), agentMobile);
        responseData.put("snList", snList);
        return responseData;
    }
}
