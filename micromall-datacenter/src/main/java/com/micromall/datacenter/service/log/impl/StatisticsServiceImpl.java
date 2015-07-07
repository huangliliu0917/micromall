package com.micromall.datacenter.service.log.impl;

import com.micromall.datacenter.dao.log.StatisticsDao;
import com.micromall.datacenter.service.log.StatisticsService;
import com.micromall.datacenter.utils.StringUtil;
import com.micromall.datacenter.viewModel.log.AgentShipmentsViewModel;
import com.micromall.datacenter.viewModel.log.GoodShipmentsViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/4.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsDao dao;

    @Transactional(readOnly = true)
    public Page<AgentShipmentsViewModel> getAgentShipments(int customerId, int levelId, String agentMobile, String beginTime, String endTime, int pageIndex, int pageSize) {
        Date beginDate = null, endDate = null;
        if (StringUtils.isEmpty(beginTime)) {
            beginDate = new Date(0);
        } else {
            beginDate = StringUtil.DateFormat(beginTime, StringUtil.DATE_PATTERN);
        }
        if (StringUtil.isEmpty(endTime)) {
            endDate = new Date();
        } else {
            endDate = StringUtil.DateFormat(endTime, StringUtil.DATE_PATTERN);
        }
        if (levelId > 0) {
            return dao.getAgentShipments(customerId, levelId, agentMobile, beginDate, endDate, new PageRequest(pageIndex - 1, pageSize));
        } else {
            return dao.getAgentShipments(customerId, agentMobile, beginDate, endDate, new PageRequest(pageIndex - 1, pageSize));
        }
    }

    @Transactional(readOnly = true)
    public List<GoodShipmentsViewModel> getGoodShipments(int customerId, String beginTime, String endTime) {
        Date beginDate = null, endDate = null;
        if (StringUtils.isEmpty(beginTime)) {
            beginDate = new Date(0);
        } else {
            beginDate = StringUtil.DateFormat(beginTime, StringUtil.DATE_PATTERN);
        }
        if (StringUtils.isEmpty(endTime)) {
            endDate = new Date();
        } else {
            endDate = StringUtil.DateFormat(endTime, StringUtil.DATE_PATTERN);
        }
        return dao.getGoodShipments(customerId, beginDate, endDate);
    }
}
