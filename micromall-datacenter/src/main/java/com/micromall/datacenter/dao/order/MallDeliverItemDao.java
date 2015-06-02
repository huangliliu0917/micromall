package com.micromall.datacenter.dao.order;

import com.micromall.datacenter.bean.orders.MallDeliverItemBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2015/5/29.
 */
public interface MallDeliverItemDao extends JpaRepository<MallDeliverItemBean, Integer> {

    List<MallDeliverItemBean> findBySnStatus(int status);

    @Modifying
    @Query("update MallDeliverItemBean itemBean set itemBean.snStatus=1 where itemBean.sn in ?1")
    void updateStatus(String[] snList);
}
