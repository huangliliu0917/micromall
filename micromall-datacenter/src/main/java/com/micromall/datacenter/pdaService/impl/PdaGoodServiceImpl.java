package com.micromall.datacenter.pdaService.impl;

import com.micromall.datacenter.pdaBean.PdaGoodBean;
import com.micromall.datacenter.pdaDao.PdaGoodDao;
import com.micromall.datacenter.pdaService.PdaGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2015/6/1.
 */
@Service
@Transactional(value = "pdaTransactionManager")
public class PdaGoodServiceImpl implements PdaGoodService {
    @Autowired
    private PdaGoodDao dao;

    public PdaGoodBean save(PdaGoodBean goodBean) {
        return dao.save(goodBean);
    }

    public void delete(String goodCode) {
        dao.setDelete(goodCode);
    }

    public void update(String currentQCode, String qName, String qTip, String oriQCode) {
        dao.update(currentQCode, qName, qTip, oriQCode);
    }
}
