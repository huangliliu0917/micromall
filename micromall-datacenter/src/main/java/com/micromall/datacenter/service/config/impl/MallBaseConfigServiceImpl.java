package com.micromall.datacenter.service.config.impl;

import com.micromall.datacenter.bean.config.MallBaseConfigBean;
import com.micromall.datacenter.dao.config.MallBaseConfigDao;
import com.micromall.datacenter.service.config.MallBaseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

/**
 * Created by Administrator on 2015/5/12.
 */
@Service
@Transactional
public class MallBaseConfigServiceImpl implements MallBaseConfigService {

    @Autowired
    private MallBaseConfigDao dao;

    public MallBaseConfigBean save(MallBaseConfigBean bean) {
        return dao.save(bean);
    }

    @Transactional(readOnly = true)
    public MallBaseConfigBean findByCustomerId(int customerId) {
        return dao.findOne(customerId);
    }
}
