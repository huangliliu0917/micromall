package com.micromall.adminWeb.controller.delivery.api;

import com.micromall.adminWeb.controller.BaseController;
import com.micromall.datacenter.bean.delivery.DeliverItemBean;
import com.micromall.datacenter.service.delivery.DeliverItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/23.
 */
@Controller
@RequestMapping("/deliverApi")
public class DeliverApiController extends BaseController {
    @Autowired
    private DeliverItemService deliverItemService;
}
