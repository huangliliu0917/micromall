package com.micromall.datacenter.dao.good;

import com.micromall.datacenter.bean.goods.MallGoodBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
    Page<MallGoodBean> findByCustomerIdAndGoodNameContaining(int customerId, String goodName, Pageable pageable);
}
