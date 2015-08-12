package com.micromall.adminWeb.controller.agent.api;

import com.micromall.adminWeb.config.ApplicationConfig;
import com.micromall.adminWeb.config.WebConfig;
import com.micromall.adminWeb.controller.SpringWebTest;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.bean.agent.MallGroupBean;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.agent.MallGroupService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by allan on 2015/7/23.
 */
@ActiveProfiles("test")
@ContextConfiguration(classes = {WebConfig.class, ApplicationConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class GroupApiControllerTest extends SpringWebTest {
    private final static Log log = LogFactory.getLog(GroupApiControllerTest.class);
    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallAgentLevelService levelService;
    @Autowired
    private MallGroupService groupService;

    private MallGroupBean mockGroup;

    private MallAgentBean mockAgent;

    private Cookie mockCookie;

    @Before
    public void setUp() throws Exception {
        MallAgentLevelBean mockLevel = new MallAgentLevelBean();
        mockLevel.setCustomerId(0);
        mockLevel.setLevelName("mockLevel");
        mockLevel = levelService.save(mockLevel);

        mockGroup = new MallGroupBean();
        mockGroup.setCustomerId(0);
        mockGroup.setGroupName("mockGroupName");
        mockGroup.setGroupDesc("mockGroupDesc");
        mockGroup = groupService.save(mockGroup);

        mockAgent = new MallAgentBean();
        mockAgent.setAddTime(new Date());
        mockAgent.setAgentAccount("15067144911");
        mockAgent.setAgentAddr("mockAddr");
        mockAgent.setAgentPassword(DigestUtils.md5Hex("123456"));
        mockAgent.setAgentStatus(1);
        mockAgent.setName("mockName");
        mockAgent.setCustomerId(0);
        mockAgent.setGroups("|" + mockGroup.getGroupId() + "|");
        mockAgent = agentService.save(mockAgent, mockLevel.getLevelId());

        mockCookie = new Cookie("UserID", "0");
        mockCookie.setMaxAge(1209600);
        mockCookie.setPath("/");
    }

    @Test
    @Rollback
    public void testSave() throws Exception {
        mockMvc.perform(post("/groupApi/save")
                .param("groupName", "mockName")
                .param("groupDesc", "mockDesc")
                .cookie(mockCookie))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
        entityManager.clear();
        managerFactory.getCache().evictAll();
        List<MallGroupBean> groupList = groupService.findAll(0);
        assertEquals(2, groupList.size());
    }

    @Test
    @Rollback
    public void testDelete() throws Exception {
        //分组被使用无法删除
        mockMvc.perform(post("/groupApi/delete")
                .param("groupId", String.valueOf(mockGroup.getGroupId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(2));
        entityManager.clear();
        managerFactory.getCache().evictAll();
        List<MallGroupBean> groupBeanList = groupService.findAll(0);
        assertEquals(1, groupBeanList.size());

        //成功删除
        MallGroupBean toDeleteBean = new MallGroupBean();
        toDeleteBean.setGroupName("name");
        toDeleteBean.setCustomerId(0);
        toDeleteBean.setGroupDesc("desc");
        toDeleteBean = groupService.save(toDeleteBean);
        mockMvc.perform(post("/groupApi/delete")
                .param("groupId", String.valueOf(toDeleteBean.getGroupId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
        entityManager.clear();
        managerFactory.getCache().evictAll();
        List<MallGroupBean> groupBeanList1 = groupService.findAll(0);
        assertEquals(1, groupBeanList.size());
    }
}