package com.micromall.datacenter.pdaDao;

import com.micromall.datacenter.pdaBean.SnInfoBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2015/5/29.
 */
public interface SnInfoDao extends JpaRepository<SnInfoBean, Integer> {

    @Query("select snInfo from SnInfoBean snInfo where substring(snInfo.qcode,1,(length(snInfo.qcode)-3))=?1 and snInfo.snStatus=?2 and snInfo.gno=?3 ")
    List<SnInfoBean> findBySnStatusAndGno(String customerId, int status, String gno);

    @Modifying
    @Query("update SnInfoBean infoBean set infoBean.snStatus=1 where infoBean.sn in :sns")
    void updateStatus(@Param("sns") List<String> snList);
}
