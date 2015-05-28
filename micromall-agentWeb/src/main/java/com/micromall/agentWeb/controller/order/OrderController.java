package com.micromall.agentWeb.controller.order;

import com.micromall.agentWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallUserBean;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.agent.MallUserService;
import com.micromall.datacenter.service.good.MallGoodsService;
import com.micromall.datacenter.service.order.MallOrderService;
import com.micromall.datacenter.viewModel.order.ShipInfoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2015/5/23.
 */
@Controller
public class OrderController extends BaseController {
    @Autowired
    private MallOrderService orderService;
    @Autowired
    private MallGoodsService goodsService;
    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallUserService userService;

    /**
     * 发起订单给已有用户
     *
     * @param sendId
     * @param model
     * @return
     */
    @RequestMapping("/order/createOrder")
    public String createOrder(@RequestParam(value = "sendId", required = false, defaultValue = "0") int sendId, Model model) {
        List<MallGoodBean> goodList = goodsService.findAll(getCustomerId(), "");
        ShipInfoViewModel shipInfo = new ShipInfoViewModel();

        MallUserBean userBean = userService.findByUserId(sendId);
        shipInfo.setShipAddr(userBean.getUserAddr());
        shipInfo.setShipMobile(userBean.getUserMobile());
        shipInfo.setShipName(userBean.getUserName());

        model.addAttribute("shipInfo", shipInfo);
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("sendId", sendId);
        model.addAttribute("goodList", goodList);

        return "order/create_order";
    }

    /**
     * 发起订单给用户，无用户则添加
     *
     * @param model
     * @return
     */
    @RequestMapping("/order/createOrderFastReg")
    public String createOrderFastReg(Model model) {
        List<MallGoodBean> goodList = goodsService.findAll(getCustomerId(), "");
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("goodList", goodList);

        return "order/create_order_fast";
    }

    /**
     * 代理商进货
     *
     * @param model
     * @return
     */
    @RequestMapping("/order/createAgentOrderIn")
    public String createAgentOrderIn(Model model) {
        List<MallGoodBean> goodList = goodsService.findAll(getCustomerId(), "");
        MallAgentBean agentBean = agentService.findByAgentId(getAgentId());
        ShipInfoViewModel shipInfo = new ShipInfoViewModel();

        goodList = goodsService.setAgentPrice(goodList, agentBean.getAgentLevel().getLevelId());
        shipInfo.setShipAddr(agentBean.getAgentAddr());
        shipInfo.setShipMobile(agentBean.getAgentAccount());
        shipInfo.setShipName(agentBean.getName());

        model.addAttribute("shipInfo", shipInfo);
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("sendId", 0);
        model.addAttribute("goodList", goodList);

        return "order/create_order";
    }

    /**
     * 给代理商主动发起订单
     *
     * @param agentId
     * @param model
     * @return
     */
    @RequestMapping("/order/createAgentOrderOut")
    public String createAgentOrderOut(int agentId, Model model) {
        List<MallGoodBean> goodList = goodsService.findAll(getCustomerId(), "");
        MallAgentBean agentBean = agentService.findByAgentId(agentId);
        goodList = goodsService.setAgentPrice(goodList, agentBean.getAgentLevel().getLevelId());
        ShipInfoViewModel shipInfo = new ShipInfoViewModel();
        shipInfo.setShipName(agentBean.getName());
        shipInfo.setShipMobile(agentBean.getAgentAccount());
        shipInfo.setShipAddr(agentBean.getAgentAddr());
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("goodList", goodList);
        model.addAttribute("shipInfo", shipInfo);
        model.addAttribute("agentId", agentId);

        return "order/create_order_agent";
    }

    @RequestMapping("/order/confirmOrder")
    public String confirmOrder(String orderId, Model model) {
        MallOrderBean orderBean = orderService.findByOrderId(orderId);
        model.addAttribute("orderBean", orderBean);
        model.addAttribute("customerId", getCustomerId());
        return "order/confirm_order";
    }

    @RequestMapping("/order/orderList")
    public String orderList(@RequestParam(value = "orderType", required = false, defaultValue = "0") int orderType,
                            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                            @RequestParam(value = "orderId", required = false, defaultValue = "") String orderId, Model model) {
        Page<MallOrderBean> pageInfo = orderService.findAll(getCustomerId(), getAgentId(), pageIndex, pageSize, orderType, orderId);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("agentId", getAgentId());
        model.addAttribute("orderType", orderType);
        model.addAttribute("orderId", orderId);

        return "order/order_list";
    }
}
