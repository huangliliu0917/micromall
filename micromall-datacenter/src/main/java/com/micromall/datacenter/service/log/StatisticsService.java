package com.micromall.datacenter.service.log;

import com.micromall.datacenter.viewModel.log.AgentShipmentsViewModel;
import com.micromall.datacenter.viewModel.log.GoodShipmentsViewModel;
import org.apache.commons.httpclient.util.DateParseException;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/4.
 */
public interface StatisticsService {
    /**
     * 代理商出货量统计
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<AgentShipmentsViewModel> getAgentShipments(int customerId, int levelId, String agentMobile, String beginTime, String endTime, int pageIndex, int pageSize);

    /**
     * 商品销售量统计
     *
     * @param customerId
     * @param beginTime
     * @param endTime
     * @return
     */
    List<GoodShipmentsViewModel> getGoodShipments(int customerId, String beginTime, String endTime);
}
