package com.micromall.datacenter.pdaDao;

import com.micromall.datacenter.pdaBean.SnBslInfoBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Administrator on 2015/6/1.
 */
public interface SnBslInfoDao extends JpaRepository<SnBslInfoBean, Integer> {
    @Query("select count(infoBean.id) from SnBslInfoBean infoBean where infoBean.mainCode=?1")
    int findSnType(String sn);
}
