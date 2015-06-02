package com.micromall.datacenter.pdaService.impl;

import com.micromall.datacenter.pdaDao.SnBslInfoDao;
import com.micromall.datacenter.pdaService.SnBslInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2015/6/1.
 */
@Service
@Transactional(value = "pdaTransactionManager")
public class SnBslInfoServiceImpl implements SnBslInfoService {
    @Autowired
    private SnBslInfoDao dao;

    /**
     * 得到sn码类型，0表示一级序列号，1表示二级序列号
     *
     * @param sn
     * @return
     */
    @Transactional(readOnly = true)
    public int findSnType(String sn) {
        if (dao.findSnType(sn) > 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
