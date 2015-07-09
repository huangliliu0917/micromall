package com.micromall.datacenter.dao.delivery;

import com.micromall.datacenter.bean.delivery.MainBarCodeBean;
import com.micromall.datacenter.bean.delivery.SubBarCodeBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2015/6/27.
 */
public interface SubBarCodeDao extends JpaRepository<SubBarCodeBean, Long> {
    List<SubBarCodeBean> findByMainBar(MainBarCodeBean codeBean);

    @Modifying
    @Query("update SubBarCodeBean subBarCode set subBarCode.locked=1 where subBarCode.subCode=?1")
    void lockedByCode(String code);

    @Query("select count(subBarCode.id) from SubBarCodeBean subBarCode where subBarCode.subCode=?1 and subBarCode.mainBar.goodBean.goodId=?2 and subBarCode.locked=0")
    int countByLocked(String code, int goodId);

    @Modifying
    @Query("update SubBarCodeBean set locked=0 where subCode=?1")
    void unLockByCode(String code);
}
