package com.micromall.adminWeb.controller.delivery.api;

import com.micromall.adminWeb.config.ApplicationConfig;
import com.micromall.adminWeb.config.WebConfig;
import com.micromall.adminWeb.controller.SpringWebTest;
import com.micromall.datacenter.bean.delivery.ManagerBean;
import com.micromall.datacenter.service.delivery.ManagerService;
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
public class ManagerApiControllerTest extends SpringWebTest {
    @Autowired
    private ManagerService managerService;

    private ManagerBean mockManager;

    private Cookie mockCookie;

    @Before
    public void setUp() throws Exception {
        mockManager = new ManagerBean();
        mockManager.setCustomerId(0);
        mockManager.setAddTime(new Date());
        mockManager.setName("mockName");
        mockManager.setPassword(DigestUtils.md5Hex("123456"));
        mockManager.setAccount("mockAccount");
        mockManager.setIsDelete(0);
        mockManager = managerService.save(mockManager);

        Cookie cookie = new Cookie("UserID", "0");
        cookie.setMaxAge(1209600);
        cookie.setPath("/");
        mockCookie = cookie;
    }

    @Test
    public void testAddManager() throws Exception {
        mockMvc.perform(post("/managerApi/addManager")
                .param("account", "testAccount")
                .param("name", "testName")
                .cookie(mockCookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1));
        ManagerBean managerBean = managerService.login("testAccount", DigestUtils.md5Hex("123456"));
        assertNotNull(managerBean);
        assertEquals(DigestUtils.md5Hex("123456"), managerBean.getPassword());
    }

    @Test
    public void testUpdateName() throws Exception {
        mockMvc.perform(post("/managerApi/updateName")
                .param("newName", "mockNewName")
                .param("managerId", String.valueOf(mockManager.getId()))
                .cookie(mockCookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1));
        entityManager.clear();
        managerFactory.getCache().evictAll();
        ManagerBean managerBean = managerService.login(mockManager.getAccount(), mockManager.getPassword());
        assertEquals("mockNewName", managerBean.getName());
    }

    @Test
    public void testResetPass() throws Exception {
        mockMvc.perform(post("/managerApi/resetPassword")
                .param("managerId", String.valueOf(mockManager.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1));
        entityManager.clear();
        managerFactory.getCache().evictAll();
        ManagerBean managerBean = managerService.login(mockManager.getAccount(), DigestUtils.md5Hex("123456"));
        assertNotNull(managerBean);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(post("/managerApi/delete")
                .param("managerId", String.valueOf(mockManager.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1));
        entityManager.clear();
        managerFactory.getCache().evictAll();
        ManagerBean managerBean = managerService.login(mockManager.getAccount(), mockManager.getPassword());
        assertNull(managerBean);
    }
}