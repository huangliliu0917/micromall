package com.micromall.datacenter.service.order.impl;

import com.micromall.datacenter.bean.orders.WaitDeliverSnInfoBean;
import com.micromall.datacenter.dao.order.WaitDeliverSnInfoDao;
import com.micromall.datacenter.pdaBean.SnInfoBean;
import com.micromall.datacenter.pdaService.SnInfoService;
import com.micromall.datacenter.service.order.WaitDeliverSnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/5/29.
 */
@Service
@Transactional
public class WaitDeliverSnInfoServiceImpl implements WaitDeliverSnInfoService {
    @Autowired
    private WaitDeliverSnInfoDao dao;
    @Autowired
    private SnInfoService infoService;

    public void batchSave(List<SnInfoBean> list) {
        for (SnInfoBean infoBean : list) {
            WaitDeliverSnInfoBean snInfoBean = new WaitDeliverSnInfoBean();
            snInfoBean.setSn(infoBean.getSn());
            snInfoBean.setSendMobile(infoBean.getGno());
            snInfoBean.setAddTime(new Date());
            snInfoBean.setProCode(infoBean.getQcode());
            snInfoBean.setSnStatus(0);
            dao.save(snInfoBean);
        }
    }

    public List<SnInfoBean> findAll(int customerId) {
        //拿到数据
        List<SnInfoBean> snInfoBeans = infoService.findBySnStatus(0);
        String[] snList = new String[snInfoBeans.size()];
        for (int i = 0; i < snInfoBeans.size(); i++) {
            snList[i] = snInfoBeans.get(i).getSn();
        }
        //更新状态
        infoService.updateStatus(snList);
        //存储到本地
        this.batchSave(snInfoBeans);
        return snInfoBeans;
    }
}
