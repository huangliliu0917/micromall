package com.micromall.datacenter.service.good.impl;

import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.dao.good.MallGoodsDao;
import com.micromall.datacenter.service.good.MallGoodsService;
import com.micromall.datacenter.viewModel.good.GoodPriceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/14.
 */
@Service
public class MallGoodsServiceImpl implements MallGoodsService {
    @Autowired
    private MallGoodsDao dao;

    @Transactional
    public MallGoodBean save(MallGoodBean bean) {
        return dao.save(bean);
    }

    @Transactional
    public void delete(int goodId) {
        dao.delete(goodId);
    }

    @Transactional(readOnly = true)
    public MallGoodBean findByGoodId(int goodId) {
        MallGoodBean goodBean = dao.findOne(goodId);
        List<GoodPriceViewModel> list = new ArrayList<GoodPriceViewModel>();
        String[] tempInfo = goodBean.getPriceInfo().split(",");
        for (String levelPrice : tempInfo) {
            String[] info = levelPrice.split(":");
            GoodPriceViewModel viewModel = new GoodPriceViewModel();
            viewModel.setLevelId(info[0]);
            viewModel.setLevelName(info[1]);
            viewModel.setPrice(info[2]);
            list.add(viewModel);
        }
        goodBean.setPriceViewModelList(list);
        return goodBean;
    }

    @Transactional(readOnly = true)
    public Page<MallGoodBean> findAll(int customerId, String goodName, int pageIndex, int pageSize) {
        return dao.findByCustomerIdAndGoodNameContaining(customerId, goodName, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "goodId")));
    }

    /**
     * 得到某个代理商等级的价格
     *
     * @param agentLevel
     * @param priceInfo  levelid:price|levelid:price
     * @return
     */
    public double getPriceByAgent(int agentLevel, String priceInfo) {
        String[] tempInfo = priceInfo.split(",");
        for (String levelPrice : tempInfo) {
            String[] info = levelPrice.split(":");
            if (Integer.parseInt(info[0]) == agentLevel) {
                return Double.parseDouble(info[1]);
            }
        }
        return 0;
    }
}
