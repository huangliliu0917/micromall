package com.micromall.datacenter.pdaDao;

import com.micromall.datacenter.pdaBean.PdaGoodBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2015/6/1.
 */
public interface PdaGoodDao extends JpaRepository<PdaGoodBean, String> {

    @Modifying
    @Query("update PdaGoodBean goodBean set goodBean.qcode=?1,goodBean.qname=?2,goodBean.qtip=?3 where goodBean.qcode=?4 and goodBean.state=0")
    void update(String currentQCode, String qName, String qTip, String oriQCode);

    @Modifying
    @Query("update PdaGoodBean goodBean set goodBean.state=2 where goodBean.qcode=?1 and goodBean.state=0")
    void setDelete(String qCode);
}
