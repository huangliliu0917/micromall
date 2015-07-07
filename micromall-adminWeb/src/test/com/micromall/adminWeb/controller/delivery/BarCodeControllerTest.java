package com.micromall.adminWeb.controller.delivery;

import com.micromall.adminWeb.config.ApplicationConfig;
import com.micromall.adminWeb.config.WebConfig;
import com.micromall.adminWeb.controller.SpringWebTest;
import com.micromall.datacenter.bean.delivery.BatchBarCodeBean;
import com.micromall.datacenter.bean.delivery.MainBarCodeBean;
import com.micromall.datacenter.bean.delivery.SubBarCodeBean;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.service.delivery.BarCodeService;
import com.micromall.datacenter.service.good.MallGoodsService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
public class BarCodeControllerTest extends SpringWebTest {
    @Autowired
    private BarCodeService codeService;
    @Autowired
    private MallGoodsService goodsService;

    private Cookie mockCookie;

    private MallGoodBean mockGood;

    private BatchBarCodeBean mockBatchBarCode;

    @Before
    public void setUp() throws Exception {
        mockGood = new MallGoodBean();
        mockGood.setAddTime(new Date());
        mockGood.setCustomerId(0);
        mockGood.setGoodName("mockGood");
        mockGood.setPrice(100);
        mockGood.setPriceInfo(1 + ":" + "mockLevel" + ":50");
        mockGood = goodsService.save(mockGood);
        codeService.createBarCode(0, 1, 12, mockGood.getGoodId());

        mockBatchBarCode = codeService.findBatchBarCodeAll(0, mockGood.getGoodId(), 0, 1, 20).getContent().get(0);

        Cookie cookie = new Cookie("UserID", "0");
        cookie.setMaxAge(1209600);
        cookie.setPath("/");
        mockCookie = cookie;
    }

    @Test
    public void testBatchBarCodeList() throws Exception {
        ModelAndView modelAndView = mockMvc.perform(get("/delivery/batchCodeList")
                .param("goodId", String.valueOf(mockGood.getGoodId()))
                .param("goodName", mockGood.getGoodName())
                .cookie(mockCookie))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();
        Map<String, Object> map = modelAndView.getModel();
        assertEquals(1, ((Page<BatchBarCodeBean>) map.get("pageInfo")).getTotalElements());
        assertEquals(mockGood.getGoodName(), String.valueOf(map.get("goodName")));
    }

    @Test
    public void testBarCodeList() throws Exception {
        ModelAndView modelAndView = mockMvc.perform(get("/delivery/barCodeList")
                .param("batchCodeId", String.valueOf(mockBatchBarCode.getId()))
                .param("goodName", mockGood.getGoodName())
                .param("goodId", String.valueOf(mockGood.getGoodId()))
                .cookie(mockCookie))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();
        Map<String, Object> map = modelAndView.getModel();
        assertEquals(1, ((Page<MainBarCodeBean>) map.get("pageInfo")).getTotalElements());
    }

    @Test
    public void testSubBarCode() throws Exception {
        MainBarCodeBean mainBarCodeBean = codeService.findMainBarCodeAll(mockBatchBarCode.getId()).get(0);
        ModelAndView modelAndView = mockMvc.perform(get("/delivery/subBarCodeList")
                .param("mainCodeId", String.valueOf(mainBarCodeBean.getId())))
                .andExpect(status().isOk())
                .andReturn().getModelAndView();
        Map<String, Object> map = modelAndView.getModel();
        assertEquals(12, ((List<SubBarCodeBean>) map.get("subBarCodeBeans")).size());
    }

    @Test
    @Ignore
    public void testPrintBarCode() throws Exception {
    }
}