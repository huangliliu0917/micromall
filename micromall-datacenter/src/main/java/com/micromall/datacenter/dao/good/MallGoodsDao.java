package com.micromall.datacenter.dao.good;

import com.micromall.datacenter.bean.goods.MallGoodBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2015/5/14.
 */
public interface MallGoodsDao extends JpaRepository<MallGoodBean, Integer>, JpaSpecificationExecutor {
    @Modifying
    @Query("update MallGoodBean good set good.isDelete=1 where good.goodId=?1")
    void delete(int goodId);

//    @Query("select good from MallGoodBean good where good.customerId=?1 and good.goodName like %?2%")
//    Page<MallGoodBean> findAll(int customerId, String goodName,Pageable pageable);

    @Query("select good from MallGoodBean good where good.isDelete=0 and good.customerId=?1 and good.goodName like %?2%")
    List<MallGoodBean> findByCustomerIdAndGoodNameContaining(int customerId, String goodName);

    @Query("select good.priceInfo from MallGoodBean good where good.goodId=?1")
    String findPriceInfo(int goodId);

    @Query("select count(goodBean.goodId) from MallGoodBean goodBean where goodBean.goodCode=?1 and goodBean.customerId=?2 and goodBean.goodId<>?3 and goodBean.isDelete=0")
    int goodCodeExists(String goodCode, int customerId, int currentGoodId);

    MallGoodBean findByCustomerIdAndGoodCode(int customerId, String goodCode);

    int countByCustomerId(int customerId);
}
