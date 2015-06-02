package com.micromall.agentWeb.controller.order.api;

import com.micromall.agentWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallUserBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.bean.orders.MallOrderItemBean;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.agent.MallUserService;
import com.micromall.datacenter.service.order.MallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallUserService userService;

    @RequestMapping("/orderApi/createOrder")
    @ResponseBody
    public Map<Object, Object> createOrder(MallOrderBean orderBean, int goodId) {
        int result = 0;
        try {
            MallAgentBean agentBean = agentService.findByAgentId(getAgentId());
            orderBean.setAddTime(new Date());
            orderBean.setCustomerId(getCustomerId());
            if (orderBean.getSendId() > 0) {
                orderBean.setDeliverPath("|" + getAgentId() + "|");
                orderBean.setRealShipId(getAgentId());
            } else {
                orderBean.setDeliverPath("|" + getAgentId() + "|" + agentBean.getSuperAgentId() + "|");
                orderBean.setRealShipId(agentBean.getSuperAgentId());
            }
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

    @RequestMapping("/orderApi/createFastRegUser")
    @ResponseBody
    public Map<Object, Object> createOrderFastRegUser(MallOrderBean orderBean, int goodId, Model model) {
        int result = 0;
        try {
            //查找用户，如果存在则选择该用户，如果不存在新建
            MallUserBean existsUser = userService.findByUserNameAndAgent(getCustomerId(), orderBean.getShipName(), getAgentId());
            if (existsUser == null) {
                //新建
                MallUserBean userBean = new MallUserBean();
                userBean.setUserName(orderBean.getShipName());
                userBean.setIsDelete(0);
                userBean.setCustomerId(getCustomerId());
                userBean.setUserAddr(orderBean.getShipAddr());
                userBean.setUserMobile(orderBean.getShipMobile());
                existsUser = userService.save(userBean, getAgentId());
            }

            //创建订单
            orderBean.setAddTime(new Date());
            orderBean.setCustomerId(getCustomerId());
            orderBean.setDeliverPath("|" + getAgentId() + "|");
            orderBean.setRealShipId(getAgentId());
            orderBean.setOrderStatus(0);
            orderBean.setOwnerId(getAgentId());
            orderBean.setSendId(existsUser.getUserId());
            orderService.create(orderBean, goodId);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping("/orderApi/createOrderAgentOut")
    @ResponseBody
    public Map<Object, Object> createOrderAgentOut(MallOrderBean orderBean, int goodId, int agentId, Model model) {
        int result = 0;
        try {
            orderBean.setAddTime(new Date());
            orderBean.setCustomerId(getCustomerId());
            orderBean.setOrderStatus(0);
            orderBean.setOwnerId(agentId);
            orderBean.setDeliverPath("|" + agentId + "|" + getAgentId() + "|");
            orderBean.setRealShipId(getAgentId());
            orderBean.setSendId(0);
            orderService.create(orderBean, goodId);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    /**
     * 确认发货
     *
     * @param orderId
     * @param proCodes
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

    /**
     * 转交订单给上级代理
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/orderApi/transferOrder")
    @ResponseBody
    public Map<Object, Object> transferOrder(String orderId) {
        int result = 0;
        try {
            MallAgentBean agentBean = agentService.findByAgentId(getAgentId());
            orderService.transferOrder(orderId, agentBean.getSuperAgentId());
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }
}
