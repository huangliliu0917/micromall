package com.micromall.datacenter.service.good;

import com.micromall.datacenter.bean.goods.MallGoodBean;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2015/5/14.
 */
public interface MallGoodsService {
    MallGoodBean save(MallGoodBean bean);
    void delete(int goodId);
    MallGoodBean findByGoodId(int goodId);
    Page<MallGoodBean> findAll(int customerId, String goodName, int pageIndex, int pageSize);
    double getPriceByAgent(int agentLevel, String priceInfo);
}
