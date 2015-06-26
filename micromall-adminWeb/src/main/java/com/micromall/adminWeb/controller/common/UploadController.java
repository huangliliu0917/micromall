package com.micromall.adminWeb.controller.common;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.utils.ResourceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/19.
 */
@Controller
public class UploadController extends BaseController {
    @Autowired
    private ResourceServer resourceServer;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<Object, Object> upLoad(@RequestParam(value = "btnFile", required = false) MultipartFile files, HttpServletRequest request) {
        int result = 0;
        Map<Object, Object> responseData = new HashMap<Object, Object>();
        try {
            String token = resourceServer.saveResource(files.getInputStream(), files.getOriginalFilename(), getCustomerId(), ResourceServer.CustomerImage);
            responseData.put("file", token);
            responseData.put("fileUri", resourceServer.resourceUri(token));
            result = 1;
        } catch (Exception e) {
            responseData.put("msg", e.getMessage());
        }
        responseData.put("result", result);

        return responseData;
    }
}
