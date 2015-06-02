package com.micromall.datacenter.pdaService;

import com.micromall.datacenter.pdaBean.SnInfoBean;

import java.util.List;

/**
 * Created by Administrator on 2015/5/29.
 */
public interface SnInfoService {
    List<SnInfoBean> findBySnStatusAndGno(int status, String gno);

    void updateStatus(List<String> sn);
}
