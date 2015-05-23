package com.micromall.adminWeb.controller.good;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.dao.good.MallGoodsDao;
import com.micromall.datacenter.service.agent.MallAgentLevelService;
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

    @RequestMapping("/good/goodList")
    public ModelAndView goodList(@RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                 @RequestParam(value = "goodName", required = false, defaultValue = "") String goodName) {
        ModelMap modelMap = new ModelMap();
        Page<MallGoodBean> pageInfo = goodsService.findAll(getCustomerId(), goodName, pageIndex, pageSize);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("pageIndex", pageIndex);
        modelMap.put("goodName", goodName);
        return new ModelAndView("good/good_list", modelMap);
    }

    @RequestMapping("/good/goodEdit")
    public ModelAndView goodEdit(@RequestParam(value = "goodId", required = false, defaultValue = "0") int goodId) {
        ModelMap modelMap = new ModelMap();
        if (goodId > 0) {
            modelMap.put("goodBean", goodsService.findByGoodId(goodId));
        }
        modelMap.put("goodId", goodId);
        List<MallAgentLevelBean> levelBeanList = levelService.findByCustomerId(getCustomerId());
        modelMap.put("levelList", levelBeanList);
        return new ModelAndView("good/good_edit", modelMap);
    }
}
