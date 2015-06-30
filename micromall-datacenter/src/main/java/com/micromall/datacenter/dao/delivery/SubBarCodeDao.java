package com.micromall.datacenter.dao.delivery;

import com.micromall.datacenter.bean.delivery.MainBarCodeBean;
import com.micromall.datacenter.bean.delivery.SubBarCodeBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2015/6/27.
 */
public interface SubBarCodeDao extends JpaRepository<SubBarCodeBean, Long> {
    List<SubBarCodeBean> findByMainBar(MainBarCodeBean codeBean);

}
