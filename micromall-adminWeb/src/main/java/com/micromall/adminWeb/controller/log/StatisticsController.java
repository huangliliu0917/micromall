package com.micromall.adminWeb.controller.log;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import com.micromall.datacenter.service.log.StatisticsService;
import com.micromall.datacenter.viewModel.log.AgentShipmentsViewModel;
import org.apache.commons.httpclient.util.DateParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2015/6/4.
 */
@Controller
public class StatisticsController extends BaseController {
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private MallAgentLevelService levelService;

    /**
     * 代理商出货量统计
     *
     * @return
     */
    @RequestMapping("/statistics/agentShipments")
    public String agentShipments(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                 @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey,
                                 @RequestParam(value = "beginTime", required = false, defaultValue = "") String beginTime,
                                 @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime,
                                 @RequestParam(value = "levelId", required = false, defaultValue = "0") int levelId, Model model) {
        Page<AgentShipmentsViewModel> pageInfo = statisticsService.getAgentShipments(getCustomerId(), levelId, searchKey, beginTime, endTime, pageIndex, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("searchKey", searchKey);
        model.addAttribute("beginTime", beginTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("levelId", levelId);

        //等级绑定
        model.addAttribute("levelList", levelService.findByCustomerId(getCustomerId()));

        return "statistics/agent_shipments";
    }

    /**
     * 商品出货量统计
     *
     * @param beginTime
     * @param endTime
     * @param model
     * @return
     */
    @RequestMapping("/statistics/goodShipments")
    public String goodShipments(@RequestParam(value = "beginTime", required = false, defaultValue = "") String beginTime,
                                @RequestParam(value = "endTime", required = false, defaultValue = "") String endTime, Model model) {
        model.addAttribute("beginTime", beginTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("list", statisticsService.getGoodShipments(getCustomerId(), beginTime, endTime));
        return "statistics/good_shipments";
    }
}
