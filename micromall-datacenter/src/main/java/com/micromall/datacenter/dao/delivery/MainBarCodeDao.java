package com.micromall.datacenter.dao.delivery;

import com.micromall.datacenter.bean.delivery.MainBarCodeBean;
import com.micromall.datacenter.bean.delivery.SubBarCodeBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
public interface MainBarCodeDao extends JpaRepository<MainBarCodeBean, Long> {
    int countByMainCode(String mainCode);

    @Query("select barCode from MainBarCodeBean barCode where barCode.batchBarCodeBean.id=?1")
    Page<MainBarCodeBean> findAll(long batchCodeId, Pageable pageable);

    @Query("select barCode from MainBarCodeBean barCode where barCode.batchBarCodeBean.id=?1")
    List<MainBarCodeBean> findByBatchCodeId(long batchCodeId);


    @Query("select mainBarCode from MainBarCodeBean mainBarCode where mainBarCode.id in ?1")
    List<SubBarCodeBean> findByMainBarIn(List<Long> mainBarId);

    @Modifying
    @Query("update MainBarCodeBean mainBarCode set mainBarCode.locked=1 where mainBarCode.mainCode=?1")
    void lockedByCode(String code);

    @Modifying
    @Query("update MainBarCodeBean set locked=1 where mainCode=?1")
    void unLockByCode(String code);

    @Query("select count(mainBarCode.id) from MainBarCodeBean mainBarCode where mainBarCode.mainCode=?1 and mainBarCode.goodBean.goodId=?2 and mainBarCode.locked=0")
    int countByLocked(String code, int goodId);
}
