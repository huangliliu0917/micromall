package com.micromall.datacenter.service.order.impl;

import com.micromall.datacenter.bean.orders.MallDeliverItemBean;
import com.micromall.datacenter.dao.order.MallDeliverItemDao;
import com.micromall.datacenter.pdaBean.SnInfoBean;
import com.micromall.datacenter.pdaDao.SnBslInfoDao;
import com.micromall.datacenter.pdaService.SnBslInfoService;
import com.micromall.datacenter.pdaService.SnInfoService;
import com.micromall.datacenter.service.good.MallGoodsService;
import com.micromall.datacenter.service.order.MallDeliverItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/5/29.
 */
@Service
@Transactional
public class MallDeliverItemServiceImpl implements MallDeliverItemService {
    @Autowired
    private MallDeliverItemDao dao;
    @Autowired
    private SnInfoService infoService;
    @Autowired
    private SnBslInfoService bslInfoService;
    @Autowired
    private MallGoodsService goodsService;

    public void batchSave(List<SnInfoBean> list, int customerId) {
        for (SnInfoBean infoBean : list) {
            MallDeliverItemBean itemBean = new MallDeliverItemBean();
            itemBean.setSn(infoBean.getSn());
            itemBean.setSendMobile(infoBean.getGno());
            itemBean.setAddTime(new Date());
            itemBean.setProCode(infoBean.getQcode());
            itemBean.setSnStatus(0);
            itemBean.setCustomerId(customerId);
            itemBean.setSnType(bslInfoService.findSnType(infoBean.getSn()));
            itemBean.setGoodBean(goodsService.findByGoodCode(customerId, infoBean.getQcode()));
            dao.save(itemBean);
        }
    }

    public List<MallDeliverItemBean> findAll(int customerId, String agentMobile) {
        //拿到数据
        List<SnInfoBean> snInfoBeans = infoService.findBySnStatusAndGno(0, agentMobile);
        List<String> snList = new ArrayList<String>();
        for (SnInfoBean bean : snInfoBeans) {
            snList.add(bean.getSn());
        }
        //更新状态
        if (snList.size() > 0) {
            infoService.updateStatus(snList);
        }
        //存储到本地
        this.batchSave(snInfoBeans, customerId);
        return dao.findBySnStatus(0);
    }

    public void updateStatus(String[] snList) {
        dao.updateStatus(snList);
    }
}
