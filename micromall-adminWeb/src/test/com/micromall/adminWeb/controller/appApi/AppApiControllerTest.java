package com.micromall.adminWeb.controller.appApi;

import com.jayway.jsonpath.JsonPath;
import com.micromall.adminWeb.config.ApplicationConfig;
import com.micromall.adminWeb.config.WebConfig;
import com.micromall.adminWeb.controller.SpringWebTest;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.bean.config.MallBaseConfigBean;
import com.micromall.datacenter.bean.delivery.DeliverItemBean;
import com.micromall.datacenter.bean.delivery.ManagerBean;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import com.micromall.datacenter.service.delivery.DeliverItemService;
import com.micromall.datacenter.service.delivery.ManagerService;
import com.micromall.datacenter.service.good.MallGoodsService;
import com.micromall.datacenter.service.order.MallOrderService;
import com.micromall.datacenter.utils.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/29.
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = {WebConfig.class, ApplicationConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AppApiControllerTest extends SpringWebTest {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private MallBaseConfigService configService;
    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallAgentLevelService levelService;
    @Autowired
    private MallGoodsService goodsService;
    @Autowired
    private MallOrderService orderService;
    @Autowired
    private DeliverItemService itemService;

    private ManagerBean mockManager;

    private MallBaseConfigBean mockConfig;

    private MallAgentBean mockAgent;

    private MallOrderBean mockOrder;

    private MallGoodBean mockGood;

    @Before
    public void setUp() throws Exception {
        mockManager = new ManagerBean();
        mockManager.setAccount("mockAccount");
        mockManager.setPassword(DigestUtils.md5Hex("123456"));
        mockManager.setName("mockName");
        mockManager.setAddTime(new Date());
        mockManager.setCustomerId(0);
        mockManager = managerService.save(mockManager);

        mockConfig = new MallBaseConfigBean();
        mockConfig.setCustomerId(0);
        mockConfig.setTitle("mockTitle");
        mockConfig = configService.save(mockConfig);

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
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(post("/app/login")
                .param("account", mockManager.getAccount())
                .param("password", DigestUtils.md5Hex("123456")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mockManager.getId()))
                .andExpect(jsonPath("$.customerName").value(mockConfig.getTitle()));

        mockMvc.perform(post("/app/login")
                .param("account", "errorAccount")
                .param("password", "errorPass"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testOrderList() throws Exception {
        //无条件查询
        String context = mockMvc.perform(get("/app/orderList")
                .param("customerId", "0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value(mockOrder.getOrderId()))
                .andReturn().getResponse().getContentAsString();
        List list = JsonPath.read(context, "$");
        assertEquals(1, list.size());

        //带条件（条件不正确）
        String context2 = mockMvc.perform(get("/app/orderList")
                .param("customerId", "0")
                .param("searchKey", "none"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List list2 = JsonPath.read(context2, "$");
        assertEquals(0, list2.size());

        MallOrderBean mockPastOrder = new MallOrderBean();
        mockPastOrder.setOrderId("2015060317153568");
        mockPastOrder.setAddTime(StringUtil.DateFormat("2015-06-29", "yyyy-MM-dd"));
        mockPastOrder.setCustomerId(0);
        mockPastOrder.setDeliverPath("|" + mockAgent.getAgentId() + "|0|");
        mockPastOrder.setOrderName("mockOrder");
        mockPastOrder.setOrderStatus(0);
        mockPastOrder.setOwnerId(mockAgent.getAgentId());
        mockPastOrder.setSendId(0);
        mockPastOrder.setShipName(mockAgent.getName());
        mockPastOrder.setShipMobile(mockAgent.getAgentAccount());
        mockPastOrder.setShipAddr(mockAgent.getAgentAddr());
        mockPastOrder.setProNum(2);
        mockPastOrder.setTotalPrice(100);
        mockPastOrder.setDeliverStatus(0);
        mockPastOrder = orderService.create(mockPastOrder, mockGood.getGoodId(), 0);
        //今日订单，期望1个
        String context3 = mockMvc.perform(get("/app/orderList")
                .param("customerId", "0")
                .param("isToday", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List list3 = JsonPath.read(context3, "$");
        assertEquals(1, list3.size());
        //全部，期望2个
        String context4 = mockMvc.perform(get("/app/orderList")
                .param("customerId", "0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List list4 = JsonPath.read(context4, "$");
        assertEquals(2, list4.size());
    }

    @Test
    public void testGetIndexInfo() throws Exception {
        mockMvc.perform(get("/app/getIndexInfo")
                .param("customerId", "0"))
                .andDo(print())
                .andExpect(jsonPath("$.todayOrderNum").value(1))
                .andExpect(jsonPath("$.undeliveredNum").value(1))
                .andExpect(jsonPath("$.deliveredNum").value(0));
    }

    @Test
    public void testGetOrderDetail() throws Exception {
        mockMvc.perform(get("/app/getOrderDetail")
                .param("orderId", mockOrder.getOrderId()))
                .andExpect(jsonPath("$.orderId").value(mockOrder.getOrderId()));
    }

    @Test
    public void testDeliverPro() throws Exception {
        String mockProCodes = "157692388115283000,157692388115283001";
        mockMvc.perform(post("/app/deliverPro")
                .param("orderId", mockOrder.getOrderId())
                .param("proCodes", mockProCodes)
                .param("customerId", "0")
                .param("managerId", String.valueOf(mockManager.getId())))
                .andDo(print())
                .andExpect(jsonPath("$").value(1));
        entityManager.clear();
        managerFactory.getCache().evictAll();
        DeliverItemBean itemBean = itemService.findByOrderId(mockOrder.getOrderId());
        assertNotNull(itemBean);
        assertEquals(mockProCodes, itemBean.getProCode());
        MallOrderBean deliveredOrder = orderService.findByOrderId(mockOrder.getOrderId());
        assertEquals(1, deliveredOrder.getDeliverStatus());
    }

    @Test
    public void testUpdatePass() throws Exception {
        String mockNewPass = DigestUtils.md5Hex("199061");
        mockMvc.perform(post("/app/updatePass")
                .param("managerId", String.valueOf(mockManager.getId()))
                .param("oldPass", mockManager.getPassword())
                .param("newPass", mockNewPass))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
        entityManager.clear();
        managerFactory.getCache().evictAll();
        ManagerBean updatedManager = managerService.findById(mockManager.getId());
        assertEquals(mockNewPass, updatedManager.getPassword());
    }
}