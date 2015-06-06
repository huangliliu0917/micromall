package com.micromall.datacenter.pdaService.impl;

import com.micromall.datacenter.pdaBean.SnInfoBean;
import com.micromall.datacenter.pdaDao.SnInfoDao;
import com.micromall.datacenter.pdaService.SnInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/5/29.
 */
@Service
@Transactional(value = "pdaTransactionManager")
public class SnInfoServiceImpl implements SnInfoService {
    @Autowired(required = false)
    private SnInfoDao dao;

    @Transactional(readOnly = true)
    public List<SnInfoBean> findBySnStatusAndGno(int status, String gno, int customerId) {
        return dao.findBySnStatusAndGno(String.valueOf(customerId), status, gno);
    }

    public void updateStatus(List<String> snList) {
        dao.updateStatus(snList);
    }
}
