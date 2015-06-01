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
@Transactional
public class SnInfoServiceImpl implements SnInfoService {
    @Autowired
    private SnInfoDao dao;

    @Transactional(readOnly = true)
    public List<SnInfoBean> findBySnStatus(int status) {
        return dao.findBySnStatus(status);
    }

    public void updateStatus(String[] snList) {
        List<String> snArray = Arrays.asList(snList);
        dao.updateStatus(snArray);
    }
}
