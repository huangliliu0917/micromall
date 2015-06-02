package com.micromall.datacenter.pdaDao;

import com.micromall.datacenter.pdaBean.PdaGoodBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2015/6/1.
 */
public interface PdaGoodDao extends JpaRepository<PdaGoodBean, String> {
}
