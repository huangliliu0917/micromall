package com.micromall.datacenter.dao.config;

import com.micromall.datacenter.bean.config.MallBaseConfigBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/5/12.
 */
public interface MallBaseConfigDao extends JpaRepository<MallBaseConfigBean,Integer> {
}
