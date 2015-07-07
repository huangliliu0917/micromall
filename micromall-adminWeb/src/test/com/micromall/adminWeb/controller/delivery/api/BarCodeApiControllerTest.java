package com.micromall.adminWeb.controller.delivery.api;

import com.micromall.adminWeb.config.ApplicationConfig;
import com.micromall.adminWeb.config.WebConfig;
import com.micromall.adminWeb.controller.SpringWebTest;
import com.micromall.datacenter.bean.delivery.BatchBarCodeBean;
import com.micromall.datacenter.bean.delivery.MainBarCodeBean;
import com.micromall.datacenter.bean.delivery.SubBarCodeBean;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.service.delivery.BarCodeService;
import com.micromall.datacenter.service.good.MallGoodsService;
import com.micromall.datacenter.utils.ResourceServer;
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

import javax.servlet.http.Cookie;
import java.io.File;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Administrator on 2015/7/1.
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = {WebConfig.class, ApplicationConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BarCodeApiControllerTest extends SpringWebTest {
    @Autowired
    private BarCodeService codeService;

    @Autowired
    private MallGoodsService goodsService;
    @Autowired
    private ResourceServer resourceServer;

    private MallGoodBean mockGood;

    private Cookie mockCookie;

    @Before
    public void setUp() throws Exception {
        mockGood = new MallGoodBean();
        mockGood.setAddTime(new Date());
        mockGood.setCustomerId(0);
        mockGood.setGoodName("mockGood");
        mockGood.setPrice(100);
        mockGood.setPriceInfo(1 + ":" + "mockLevel" + ":50");
        mockGood = goodsService.save(mockGood);

        Cookie cookie = new Cookie("UserID", "0");
        cookie.setMaxAge(1209600);
        cookie.setPath("/");
        mockCookie = cookie;
    }

    @Test
    public void testCreateBarCode() throws Exception {
        mockMvc.perform(post("/barCodeApi/createBarCode")
                .param("goodId", String.valueOf(mockGood.getGoodId()))
                .param("mainCodeNum", "1")
                .param("subCodeNum", "10")
                .cookie(mockCookie))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1));
        entityManager.clear();
        managerFactory.getCache().evictAll();
        Page<BatchBarCodeBean> pageInfo = codeService.findBatchBarCodeAll(0, mockGood.getGoodId(), 0, 1, 20);
        assertEquals(1, pageInfo.getTotalElements());
        List<MainBarCodeBean> codeBeans = codeService.findMainBarCodeAll(pageInfo.getContent().get(0).getId());
        assertEquals(1, codeBeans.size());
        List<SubBarCodeBean> subBarCodeBeans = codeService.findSubBarCodeByMainCode(codeBeans.get(0).getId());
        assertEquals(10, subBarCodeBeans.size());
        //É¾³ý×ÊÔ´
        codeService.batchDelete(pageInfo.getContent().get(0).getId());
    }

    @Test
    public void testDeleteBarCode() throws Exception {
        codeService.createBarCode(0, 1, 12, mockGood.getGoodId());
        Page<BatchBarCodeBean> pageInfo = codeService.findBatchBarCodeAll(0, mockGood.getGoodId(), 0, 1, 20);
        BatchBarCodeBean mockBatchBarCode = pageInfo.getContent().get(0);
        List<MainBarCodeBean> list = codeService.findMainBarCodeAll(mockBatchBarCode.getId());
        mockMvc.perform(post("/barCodeApi/deleteBarCode")
                .param("batchCodeId", String.valueOf(mockBatchBarCode.getId()))
                .cookie(mockCookie))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1));

        File file = new File(resourceServer + list.get(0).getMainCodeImg());
        assertTrue(!file.exists());
    }

    @Test
    public void testUpdatePrinted() throws Exception {
        codeService.createBarCode(0, 1, 12, mockGood.getGoodId());
        Page<BatchBarCodeBean> pageInfo = codeService.findBatchBarCodeAll(0, mockGood.getGoodId(), 0, 1, 20);
        BatchBarCodeBean mockBatchBarCode = pageInfo.getContent().get(0);
        mockMvc.perform(post("/barCodeApi/updatePrinted")
                .param("batchCodeId", String.valueOf(mockBatchBarCode.getId()))
                .cookie(mockCookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1));
        entityManager.clear();
        managerFactory.getCache().evictAll();
        BatchBarCodeBean updatedBatchBarCode = codeService.findByBacthBarCodeId(mockBatchBarCode.getId());
        assertEquals(1, updatedBatchBarCode.getPrinted());
    }
}