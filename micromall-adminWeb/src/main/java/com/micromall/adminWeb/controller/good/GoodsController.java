package com.micromall.adminWeb.controller.good;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.bean.agent.MallGroupBean;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.dao.good.MallGoodsDao;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
import com.micromall.datacenter.service.agent.MallGroupService;
import com.micromall.datacenter.service.good.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2015/5/19.
 */
@Controller
public class GoodsController extends BaseController {
    @Autowired
    private MallGoodsService goodsService;
    @Autowired
    private MallAgentLevelService levelService;
    @Autowired
    private MallGroupService groupService;

    @RequestMapping("/good/goodList")
    public ModelAndView goodList(@RequestParam(value = "goodName", required = false, defaultValue = "") String goodName) throws JsonProcessingException {
        ModelMap modelMap = new ModelMap();
        List<MallGoodBean> goodList = goodsService.findAll(getCustomerId(), goodName);
        modelMap.put("goodList", goodList);
        modelMap.put("goodName", goodName);
        List<MallGroupBean> groupList = groupService.findAll(getCustomerId());
        ObjectMapper objectMapper = new ObjectMapper();
        modelMap.put("groupListJson", objectMapper.writeValueAsString(groupList));
        modelMap.put("groupList", groupList);
        return new ModelAndView("good/good_list", modelMap);
    }

    @RequestMapping("/good/goodEdit")
    public ModelAndView goodEdit(@RequestParam(value = "goodId", required = false, defaultValue = "0") int goodId) {
        ModelMap modelMap = new ModelMap();
        if (goodId > 0) {
            modelMap.put("goodBean", goodsService.findByGoodId(goodId));
        } else {
            modelMap.put("createCode", goodsService.getGoodCode(getCustomerId()));
        }
        modelMap.put("goodId", goodId);
        List<MallAgentLevelBean> levelBeanList = levelService.findByCustomerId(getCustomerId());
        modelMap.put("levelList", levelBeanList);
        modelMap.put("groupList", groupService.findAll(getCustomerId()));
        return new ModelAndView("good/good_edit", modelMap);
    }
}
