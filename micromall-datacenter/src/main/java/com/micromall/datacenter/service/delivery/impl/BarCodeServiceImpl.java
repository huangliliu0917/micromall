package com.micromall.datacenter.service.delivery.impl;

import com.google.zxing.WriterException;
import com.micromall.datacenter.bean.delivery.BarCodeBean;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.dao.delivery.BarCodeDao;
import com.micromall.datacenter.service.delivery.BarCodeService;
import com.micromall.datacenter.utils.BarCodeUnit;
import com.micromall.datacenter.utils.ResourceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
@Service
public class BarCodeServiceImpl implements BarCodeService {
    @Autowired
    private BarCodeDao dao;
    @Autowired
    private ResourceServer resourceServer;

    @Transactional
    public int createBarCode(int customerId, int mainCodeNum, int subCodeNum, int goodId) throws IOException, WriterException {
        Date addTime = new Date();
        int index = 0;
        List<String> mainCodeArray = createMainCode(mainCodeNum);
        for (String mainCode : mainCodeArray) {
            List<String> subCodeArray = createSubCode(mainCode, subCodeNum);
            for (String subCode : subCodeArray) {
                BarCodeBean codeBean = new BarCodeBean();
                codeBean.setMainCode(mainCode);
                codeBean.setSubCode(subCode);
                codeBean.setCustomerId(customerId);
                MallGoodBean goodBean = new MallGoodBean();
                goodBean.setGoodId(goodId);
                codeBean.setGoodBean(goodBean);
                codeBean.setAddTime(addTime);
                codeBean.setMainCodeImg(BarCodeUnit.getBarCode(mainCode, 200, 50, customerId));
                codeBean.setSubCodeImg(BarCodeUnit.getBarCode(subCode, 200, 50, customerId));
                dao.save(codeBean);
                index++;
            }
        }
        return index;
    }

    @Transactional
    public int batchDelete(String codeArray) throws IOException {
        int index = 0;
        String[] codeList = codeArray.split(",");
        for (String idStr : codeList) {
            long id = Long.parseLong(idStr);
            BarCodeBean codeBean = dao.findOne(id);

            //删除数据表数据
            dao.delete(id);
            //删除图片
            resourceServer.deleteResource(codeBean.getMainCodeImg());
            resourceServer.deleteResource(codeBean.getSubCodeImg());
            index++;
        }
        return index;
    }

    @Transactional(readOnly = true)
    public BarCodeBean findById(long id) {
        return dao.findOne(id);
    }

    @Transactional(readOnly = true)
    public Page<BarCodeBean> findAll(int customerId, int goodId, int printed, int pageIndex, int pageSize) {
        return dao.findAll(customerId, goodId, printed, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "id")));
    }

    /**
     * 生成主码
     *
     * @return
     */
    private List<String> createMainCode(int mainCodeNum) {
        List<String> mainCodeArray = new ArrayList<String>();
        for (int i = 0; i < mainCodeNum; i++) {
            while (true) {
                StringBuilder buffer = new StringBuilder("15");
                for (int j = 0; j < 13; j++) {
                    int index = (int) (Math.random() * 10);
                    buffer.append(index);
                }
                if (dao.countByMainCode(buffer.toString()) == 0) {
                    mainCodeArray.add(buffer.toString());
                    break;
                }
            }
        }
        return mainCodeArray;
    }

    /**
     * 生成副码
     *
     * @return
     */
    private List<String> createSubCode(String mainCode, int subCodeNum) {
        List<String> subCodeArray = new ArrayList<String>();
        for (int i = 0; i < subCodeNum; i++) {
            StringBuilder builder = new StringBuilder(mainCode);
            String suffix = "000" + i;
            suffix = suffix.substring(suffix.length() - 3, suffix.length());
            builder.append(suffix);
            subCodeArray.add(builder.toString());
        }
        return subCodeArray;
    }
}
