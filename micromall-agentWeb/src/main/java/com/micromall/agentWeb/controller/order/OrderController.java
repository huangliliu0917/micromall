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

    @RequestMapping("/order/createOrder")
    public String createOrder(@RequestParam(value = "sendId", required = false, defaultValue = "0") int sendId, Model model) {
        List<MallGoodBean> goodList = goodsService.findAll(getCustomerId(), "");
        model.addAttribute("goodList", goodList);
        ShipInfoViewModel shipInfo = new ShipInfoViewModel();
        MallAgentBean agentBean = agentService.findByAgentId(getAgentId());
        if (sendId > 0) {
            MallUserBean userBean = userService.findByUserId(sendId);
            shipInfo.setShipAddr(userBean.getUserAddr());
            shipInfo.setShipMobile(userBean.getUserMobile());
            shipInfo.setShipName(userBean.getUserName());

        } else {

            shipInfo.setShipAddr(agentBean.getAgentAddr());
            shipInfo.setShipMobile(agentBean.getAgentAccount());
            shipInfo.setShipName(agentBean.getName());
        }
        model.addAttribute("shipInfo", shipInfo);
        model.addAttribute("customerId", getCustomerId());
        model.addAttribute("agentBean", agentBean);
        model.addAttribute("sendId", sendId);

        return "order/create_order";
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
                            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex, Model model) {
        Page<MallOrderBean> pageInfo = orderService.findAll(getCustomerId(), getAgentId(), pageIndex, pageSize, orderType);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("customerId", getCustomerId());

        return "order/order_list";
    }
}
