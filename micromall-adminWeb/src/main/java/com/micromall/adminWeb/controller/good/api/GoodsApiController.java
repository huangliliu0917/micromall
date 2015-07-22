package com.micromall.adminWeb.controller.good.api;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.service.good.MallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/21.
 */
@Controller
@Scope("request")
public class GoodsApiController extends BaseController {
    @Autowired
    private MallGoodsService goodsService;

    @RequestMapping(value = "/goodApi/saveGood", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> saveGood(MallGoodBean goodBean) {
        int result = 0;
        try {
            if (goodsService.goodCodeExists(goodBean.getGoodCode(), getCustomerId(), goodBean.getGoodId())) {
                result = 2;
            } else {
                if (goodBean.getGoodId() > 0) {
                    MallGoodBean preGood = goodsService.findByGoodId(goodBean.getGoodId());
                    preGood.setGoodName(goodBean.getGoodName());
                    preGood.setGoodCode(goodBean.getGoodCode());
                    preGood.setGoodImg(goodBean.getGoodImg());
                    preGood.setPriceInfo(goodBean.getPriceInfo());
                    preGood.setGoodDesc(goodBean.getGoodDesc());
                    preGood.setPrice(goodBean.getPrice());
                    preGood.setGroups(goodBean.getGroups());
                    goodBean = preGood;
                } else {
                    goodBean.setCustomerId(getCustomerId());
                    goodBean.setIsDelete(0);
                    goodBean.setAddTime(new Date());
                }
                goodsService.save(goodBean);
                result = 1;
            }
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping(value = "/goodApi/setDelete", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> setDelete(int goodId) {
        int result = 0;
        try {
            goodsService.delete(goodId);
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);
        return responseData;
    }

    private Map<Object, Object> responseData = new HashMap<Object, Object>();
}
