package com.micromall.datacenter.service.config;

import com.micromall.datacenter.bean.config.MallBaseConfigBean;

/**
 * Created by Administrator on 2015/5/12.
 */
public interface MallBaseConfigService {
    MallBaseConfigBean save(MallBaseConfigBean bean);
    MallBaseConfigBean findByCustomerId(int customerid);
}
