package com.micromall.adminWeb.controller.order.api;

import com.micromall.adminWeb.config.ApplicationConfig;
import com.micromall.adminWeb.config.WebConfig;
import com.micromall.adminWeb.controller.SpringWebTest;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.bean.delivery.DeliverItemBean;
import com.micromall.datacenter.bean.delivery.ManagerBean;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.delivery.DeliverItemService;
import com.micromall.datacenter.service.delivery.ManagerService;
import com.micromall.datacenter.service.good.MallGoodsService;
import com.micromall.datacenter.service.order.MallOrderService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;

import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Administrator on 2015/7/2.
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = {WebConfig.class, ApplicationConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrderApiControllerTest extends SpringWebTest {
    @Autowired
    private MallOrderService orderService;
    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallAgentLevelService levelService;
    @Autowired
    private MallGoodsService goodsService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private DeliverItemService itemService;

    private MallOrderBean mockOrder;

    private MallAgentBean mockAgent;

    private MallGoodBean mockGood;

    private Cookie mockCookie;

    private DeliverItemBean mockItem;

    @Before
    public void setUp() throws Exception {
        MallAgentLevelBean mockLevel = new MallAgentLevelBean();
        mockLevel.setCustomerId(0);
        mockLevel.setLevelName("mockLevel");
        mockLevel = levelService.save(mockLevel);

        mockAgent = new MallAgentBean();
        mockAgent.setAddTime(new Date());
        mockAgent.setAgentAccount("15067144911");
        mockAgent.setAgentAddr("mockAddr");
        mockAgent.setAgentPassword(DigestUtils.md5Hex("123456"));
        mockAgent.setAgentStatus(1);
        mockAgent.setName("mockName");
        mockAgent.setCustomerId(0);
        mockAgent = agentService.save(mockAgent, mockLevel.getLevelId());

        mockGood = new MallGoodBean();
        mockGood.setAddTime(new Date());
        mockGood.setCustomerId(0);
        mockGood.setGoodName("mockGood");
        mockGood.setPrice(100);
        mockGood.setPriceInfo(mockLevel.getLevelId() + ":" + mockLevel.getLevelName() + ":50");
        mockGood = goodsService.save(mockGood);

        mockOrder = new MallOrderBean();
        mockOrder.setOrderId("2015060317153567");
        mockOrder.setAddTime(new Date());
        mockOrder.setCustomerId(0);
        mockOrder.setDeliverPath("|" + mockAgent.getAgentId() + "|0|");
        mockOrder.setOrderName("mockOrder");
        mockOrder.setOrderStatus(0);
        mockOrder.setOwnerId(mockAgent.getAgentId());
        mockOrder.setSendId(0);
        mockOrder.setShipName(mockAgent.getName());
        mockOrder.setShipMobile(mockAgent.getAgentAccount());
        mockOrder.setShipAddr(mockAgent.getAgentAddr());
        mockOrder.setProNum(2);
        mockOrder.setTotalPrice(100);
        mockOrder.setDeliverStatus(0);
        mockOrder = orderService.create(mockOrder, mockGood.getGoodId(), 0);

        ManagerBean mockManager = new ManagerBean();
        mockManager.setAccount("mockAccount");
        mockManager.setPassword(DigestUtils.md5Hex("123456"));
        mockManager.setName("mockName");
        mockManager.setAddTime(new Date());
        mockManager.setCustomerId(0);
        mockManager = managerService.save(mockManager);

        mockItem = new DeliverItemBean();
        mockItem.setDeliverStatus(0);
        mockItem.setCustomerId(0);
        mockItem.setAddTime(new Date());
        mockItem.setManagerBean(mockManager);
        mockItem.setOrderBean(mockOrder);
        mockItem.setProCode("123213212323");
        itemService.save(mockItem);

        mockCookie = new Cookie("UserID", "0");
        mockCookie.setMaxAge(1209600);
        mockCookie.setPath("/");
    }

    @Test
    public void testConfirmShip() throws Exception {
        mockMvc.perform(post("/orderApi/confirmShip")
                .param("orderId", mockOrder.getOrderId())
                .param("logiName", "testName")
                .param("logiNum", "testNum")
                .cookie(mockCookie))
                .andExpect(status().isOk());
        entityManager.clear();
        managerFactory.getCache().evictAll();
        MallOrderBean updatedOrder = orderService.findByOrderId(mockOrder.getOrderId());
        DeliverItemBean updatedItem = itemService.findByOrderId(mockOrder.getOrderId());
        assertEquals(mockItem.getProCode(), updatedOrder.getOrderItems().iterator().next().getProCode());
        assertEquals(1, updatedOrder.getOrderStatus());
        assertEquals(2, updatedItem.getDeliverStatus());
    }

    @Test
    public void testReDeliver() throws Exception {

    }
}