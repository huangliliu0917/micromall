package com.micromall.datacenter.service.good;

import com.micromall.datacenter.bean.goods.MallGoodBean;

import java.util.List;

/**
 * Created by Administrator on 2015/5/14.
 */
public interface MallGoodsService {
    MallGoodBean save(MallGoodBean bean);

    void delete(int goodId);

    MallGoodBean findByGoodId(int goodId);

    List<MallGoodBean> findAll(int customerId, String goodName);

    double getPriceByAgent(int agentLevel, String priceInfo);

    List<MallGoodBean> setAgentPrice(List<MallGoodBean> goodList, int agentLevel);

    String findPriceInfo(int goodId);

    boolean goodCodeExists(String goodCode, int customerId, int currentGoodId);

    MallGoodBean findByGoodCode(int customerId, String goodCode);

    /**
     * δɾ������Ʒ����
     *
     * @param customerId
     * @return
     */
    int countByCustomerId(int customerId);

    /**
     * �õ���Ʒ���
     *
     * @param customerId
     * @return
     */
    String getGoodCode(int customerId);

    /**
     * 得到代理商可查看的商品列表
     *
     * @param customerId
     * @param agentId
     * @return
     */
    List<MallGoodBean> findCheckableGoods(int customerId, int agentId);
}
