package com.micromall.datacenter.dao.delivery;

import com.micromall.datacenter.bean.delivery.BarCodeBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2015/6/25.
 */
public interface BarCodeDao extends JpaRepository<BarCodeBean, Long> {
    int countByMainCode(String mainCode);

    @Query("select barCode from BarCodeBean barCode where barCode.customerId=?1 and barCode.goodBean.goodId=?2 and barCode.printed=?3")
    Page<BarCodeBean> findAll(int customerId, int goodId, int printed, Pageable pageable);
}
