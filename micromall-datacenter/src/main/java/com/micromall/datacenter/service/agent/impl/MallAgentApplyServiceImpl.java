package com.micromall.datacenter.service.agent.impl;

import com.micromall.datacenter.bean.agent.MallAgentApplyBean;
import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.bean.config.MallBaseConfigBean;
import com.micromall.datacenter.dao.agent.MallAgentApplyDao;
import com.micromall.datacenter.service.agent.MallAgentApplyService;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import com.micromall.datacenter.utils.SMSHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2015/5/28.
 */
@Service
@Transactional
public class MallAgentApplyServiceImpl implements MallAgentApplyService {
    @Autowired
    private MallAgentApplyDao dao;
    @Autowired
    private MallAgentService agentService;
    @Autowired
    private MallBaseConfigService configService;
    @Autowired
    private MallAgentLevelService levelService;

    public MallAgentApplyBean save(MallAgentApplyBean applyBean) {
        return dao.save(applyBean);
    }

    public void updateApplyStataus(int applyId, int superAgentId, String password, int levelId, int applyStatus, String refuseReason) {
        dao.updateApplyStatus(applyStatus, refuseReason, applyId);
        MallAgentApplyBean applyBean = findByApplyId(applyId);

        if (applyStatus == 1) {
            //审核通过
            MallAgentBean agentBean = new MallAgentBean();
            agentBean.setAddTime(new Date());
            agentBean.setAgentAccount(applyBean.getMobile());
            agentBean.setAgentArea(applyBean.getArea());
            agentBean.setAgentCardId(applyBean.getCardId());
            agentBean.setSuperAgentId(superAgentId);
            agentBean.setAgentPassword(password);
            agentBean.setAgentStatus(1);
            agentBean.setAgentWeixin(applyBean.getWeixin());
            agentBean.setCustomerId(applyBean.getCustomerId());
            agentBean.setIsDelete(0);
            agentBean.setName(applyBean.getName());
            MallAgentBean resultBean = agentService.save(agentBean, levelId);

            //发送短信
            MallAgentBean superAgent = agentService.findByAgentId(superAgentId);
            MallBaseConfigBean configBean = configService.findByCustomerId(applyBean.getCustomerId());
            SMSHelper.send(applyBean.getMobile(), String.format("恭喜您成为%s的代理商，当前代理级别为%s。您的上级代理是：%s，上级联系电话：%s。请关注公众号登录，登录账号为您的手机号，登录密码为您手机号的末6位",
                    configBean.getTitle(), resultBean.getAgentLevel().getLevelName(), superAgent.getName(), superAgent.getAgentAccount()));
        } else {
            //发送短信
            MallBaseConfigBean configBean = configService.findByCustomerId(applyBean.getCustomerId());
            SMSHelper.send(applyBean.getMobile(), String.format("感谢您关注%s,抱歉您还无法成为我们代理商，理由：%s", configBean.getTitle(), applyBean.getRefuseReason()));
        }
    }

    @Transactional(readOnly = true)
    public Page<MallAgentApplyBean> findByCustomerId(int customerId, String searchKey, int applyStatus, int pageIndex, int pageSize) {
        if (applyStatus == -1) {
            return dao.findByCustomerId(customerId, searchKey, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "applyId")));
        } else {
            return dao.findByCustomerId(customerId, searchKey, applyStatus, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "applyId")));
        }
    }

    @Transactional(readOnly = true)
    public MallAgentApplyBean findByApplyId(int applyId) {
        MallAgentApplyBean applyBean = dao.findOne(applyId);
        if (applyBean.getApplyLevelId() > 0) {
            applyBean.setApplyLevelName(levelService.findByLevelId(applyBean.getApplyLevelId()).getLevelName());
        }
        return applyBean;
    }
}
