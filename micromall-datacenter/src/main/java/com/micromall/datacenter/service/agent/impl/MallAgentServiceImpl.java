package com.micromall.datacenter.service.agent.impl;

import com.micromall.datacenter.bean.agent.MallAgentBean;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.bean.config.MallBaseConfigBean;
import com.micromall.datacenter.dao.agent.MallAgentDao;
import com.micromall.datacenter.service.agent.MallAgentService;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import com.micromall.datacenter.utils.SMSHelper;
import com.micromall.datacenter.utils.StringUtil;
import com.micromall.datacenter.viewModel.agent.MallAgentSearchViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/5/13.
 */
@Service
@Transactional
public class MallAgentServiceImpl implements MallAgentService {

    @Autowired
    private MallAgentDao dao;
    @Autowired
    private MallBaseConfigService configService;
    @Autowired
    private Environment env;

    /**
     * 保存一个实体
     *
     * @param bean
     * @return
     */
    public MallAgentBean save(MallAgentBean bean, int levelId) {
        MallAgentLevelBean levelBean = new MallAgentLevelBean();
        levelBean.setLevelId(levelId);
        bean.setAgentLevel(levelBean);
        if (bean.getAgentId() == 0) {
            bean.setAuthorizationCode(this.createAuthorizationCode(bean.getCustomerId()));
        }
        return dao.save(bean);
    }

    /**
     * 根据主键id得到实体
     *
     * @param agentId
     * @return
     */
    @Transactional(readOnly = true)
    public MallAgentBean findByAgentId(int agentId) {
        return dao.findOne(agentId);
    }

    /**
     * 分页查询
     *
     * @param searchBean 搜索的字段打包
     * @param pageIndex  页码
     * @param pageSize   每页数据量
     * @return
     */
    @Transactional(readOnly = true)
    public Page<MallAgentBean> findAll(final MallAgentSearchViewModel searchBean, int pageIndex, int pageSize, final int superAgentId) {
        Specification<MallAgentBean> specification = new Specification<MallAgentBean>() {
            public Predicate toPredicate(Root<MallAgentBean> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (superAgentId != -1) {
                    list.add(criteriaBuilder.equal(root.get("superAgentId").as(Integer.class), superAgentId));
                }

                if (searchBean != null) {
                    if (searchBean.getCustomerId() > 0) {
                        list.add(criteriaBuilder.equal(root.get("customerId").as(Integer.class), searchBean.getCustomerId()));
                    }
                    if (StringUtil.isNotEmpty(searchBean.getAgentName())) {
                        list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + searchBean.getAgentName() + "%"));
                    }
                    if (searchBean.getAgentLevel() > 0) {
                        MallAgentLevelBean levelBean = new MallAgentLevelBean();
                        levelBean.setLevelId(searchBean.getAgentLevel());
                        list.add(criteriaBuilder.equal(root.get("agentLevel").as(MallAgentLevelBean.class), levelBean));
                    }
                    if (StringUtil.isNotEmpty(searchBean.getAgentAccount())) {
                        list.add(criteriaBuilder.like(root.get("agentAccount").as(String.class), "%" + searchBean.getAgentAccount() + "%"));
                    }
                    if (searchBean.getAgentStatus() != -1) {
                        list.add(criteriaBuilder.equal(root.get("agentStatus").as(Integer.class), searchBean.getAgentStatus()));
                    }
                    if (searchBean.getGroupId() > 0) {
                        Predicate p1 = criteriaBuilder.like(root.get("groups").as(String.class), "%|" + searchBean.getGroupId() + "|%");
                        Predicate p2 = criteriaBuilder.equal(root.get("groups").as(String.class), "all");
                        list.add(criteriaBuilder.or(p1, p2));
                    }
                }

                Predicate[] p = new Predicate[list.size()];
                criteriaQuery.where(criteriaBuilder.and(list.toArray(p)));
                return criteriaQuery.getRestriction();
            }
        };
        return dao.findAll(specification, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "agentId")));
    }

    /**
     * 设置冻结和解冻
     *
     * @param isDelete
     * @param agentId
     */
    public void setDelete(int isDelete, int agentId) {
        dao.setDelete(isDelete, agentId);
        dao.flush();
    }

    /**
     * 设置申请状态，申请中，已通过，未通过
     *
     * @param agentId
     * @param agentStatus
     */
    public void updateAgentStatus(int agentStatus, String refuseReason, int agentId) {
        dao.updateAgentStatus(agentStatus, refuseReason, agentId);

        if (env.acceptsProfiles("prod")) {
            //发送短信
            MallAgentBean agentBean = this.findByAgentId(agentId);
            MallAgentBean superAgent = this.findByAgentId(agentBean.getSuperAgentId());
            MallBaseConfigBean configBean = configService.findByCustomerId(agentBean.getCustomerId());
            if (agentStatus == 1) {
                if (superAgent == null) {
                    SMSHelper.send(agentBean.getAgentAccount(), String.format("恭喜您成为%s的代理商，代理等级为：%s，祝您生活愉快",
                            configBean.getTitle(), agentBean.getAgentLevel().getLevelName()));
                } else {
                    SMSHelper.send(agentBean.getAgentAccount(), String.format("恭喜您成为%s的代理商，当前代理级别为%s，您的上级代理是：%s，上级联系电话：%s。请关注公众号",
                            configBean.getTitle(), agentBean.getAgentLevel().getLevelName(), superAgent.getName(), superAgent.getAgentAccount()));
                }
            } else {
                SMSHelper.send(agentBean.getAgentAccount(), String.format("由于一些原因您无法成为%s的代理商，理由：%s", configBean.getTitle(), refuseReason));
            }
        }
    }

    @Transactional(readOnly = true)
    public MallAgentBean checkLogin(String account, String password, int customerId) {
        return dao.checkLogin(account, password, customerId);
    }

    @Transactional(readOnly = true)
    public List<MallAgentBean> findAgentByAgentLevel(MallAgentLevelBean levelBean, int customerId) {
        return dao.findByAgentLevel(customerId, levelBean);
    }

    @Transactional(readOnly = true)
    public List<MallAgentBean> findByAgentLevel(int customerId, int agentLevel) {
        return dao.findByAgentLevel(customerId, agentLevel);
    }

    @Transactional(readOnly = true)
    public boolean accountExist(String account, int customerId) {
        return dao.accountExist(account, customerId) > 0 ? true : false;
    }

    @Transactional(readOnly = true)
    public Page<MallAgentBean> findBySearchKey(int customerId, String searchKey, int pageIndex, int pageSize, int superAgentId) {
        return dao.findBySearchKey(customerId, searchKey, superAgentId, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "agentId")));
    }

    @Transactional(readOnly = true)
    public MallAgentLevelBean findAgentLevel(int agentId) {
        return dao.findAgentLevel(agentId);
    }

    public int updatePassword(String newPass, int agentId, String originalPass) {
        return dao.updatePassword(newPass, agentId, originalPass);
    }

    public int getUnderAgentNum(int superAgentId) {
        return dao.getUnderAgentNum(superAgentId);
    }

    public void updateAddr(String newAddr, int agentId) {
        dao.updateAddr(newAddr, agentId);
    }

    public MallAgentBean findByAgentAccountAndCustomerId(String agentAccount, int customerId) {
        return dao.findByAgentAccountAndCustomerId(agentAccount, customerId);
    }

    private String createAuthorizationCode(int customerId) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXWZ0123456789";
        Random random = new Random();

        while (true) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 10; i++) {
                int number = random.nextInt(base.length());
                sb.append(base.charAt(number));
            }
            if (dao.codeExists(sb.toString(), customerId) == 0) {
                return sb.toString();
            }
        }
    }
}
