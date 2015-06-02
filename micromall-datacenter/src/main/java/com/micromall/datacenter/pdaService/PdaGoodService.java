package com.micromall.datacenter.pdaService;

import com.micromall.datacenter.pdaBean.PdaGoodBean;

/**
 * Created by Administrator on 2015/6/1.
 */
public interface PdaGoodService {
    PdaGoodBean save(PdaGoodBean goodBean);

    void delete(String goodCode);
}
