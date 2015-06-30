package com.micromall.datacenter.dao.delivery;

import com.micromall.datacenter.bean.delivery.BatchBarCodeBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2015/6/29.
 */
public interface BatchBarCodeDao extends JpaRepository<BatchBarCodeBean, Long> {
    @Query("select batchBean from BatchBarCodeBean batchBean where batchBean.customerId=?1 and batchBean.goodBean.goodId=?2 and batchBean.printed=?3")
    Page<BatchBarCodeBean> findAll(int customerId, int goodId, int printed, Pageable pageable);

    @Modifying
    @Query("update BatchBarCodeBean batchBean set batchBean.printed=1 where batchBean.id=?1")
    void updatePrinted(long batchCodeId);
}
