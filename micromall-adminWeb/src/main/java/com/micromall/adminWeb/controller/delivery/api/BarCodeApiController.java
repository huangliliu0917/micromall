package com.micromall.adminWeb.controller.delivery.api;

import com.google.zxing.WriterException;
import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.service.delivery.BarCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/26.
 */
@Controller
@RequestMapping("/barCodeApi")
public class BarCodeApiController extends BaseController {
    @Autowired
    private BarCodeService codeService;

    @RequestMapping(value = "/createBarCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> createBarCode(int goodId, int mainCodeNum, int subCodeNum) throws IOException, WriterException {
        Map<Object, Object> responseData = new HashMap<Object, Object>();
        int result = codeService.createBarCode(getCustomerId(), mainCodeNum, subCodeNum, goodId);
        responseData.put("result", result);
        return responseData;
    }

    @RequestMapping(value = "/deleteBarCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> deleteBarCode(String codeArray) throws IOException {
        Map<Object, Object> responseData = new HashMap<Object, Object>();
        int result = codeService.batchDelete(codeArray);
        responseData.put("result", result);
        return responseData;
    }
}
