package com.micromall.datacenter.service.delivery.impl;

import com.google.zxing.WriterException;
import com.micromall.datacenter.bean.delivery.BatchBarCodeBean;
import com.micromall.datacenter.bean.delivery.MainBarCodeBean;
import com.micromall.datacenter.bean.delivery.SubBarCodeBean;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.dao.delivery.BatchBarCodeDao;
import com.micromall.datacenter.dao.delivery.MainBarCodeDao;
import com.micromall.datacenter.dao.delivery.SubBarCodeDao;
import com.micromall.datacenter.service.delivery.BarCodeService;
import com.micromall.datacenter.utils.BarCodeUnit;
import com.micromall.datacenter.utils.ResourceServer;
import com.micromall.datacenter.utils.StringUtil;
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
    private MainBarCodeDao mainBarCodeDao;
    @Autowired
    private SubBarCodeDao subBarCodeDao;
    @Autowired
    private ResourceServer resourceServer;
    @Autowired
    private BarCodeUnit barCodeUnit;
    @Autowired
    private BatchBarCodeDao batchBarCodeDao;

    @Transactional
    public int createBarCode(int customerId, int mainCodeNum, int subCodeNum, int goodId) throws IOException, WriterException {
        Date addTime = new Date();
        //保存批次
        BatchBarCodeBean batchBarCodeBean = new BatchBarCodeBean();
        batchBarCodeBean.setBatchCode(StringUtil.DateFormat(addTime, "yyyyMMddHHmmss"));
        batchBarCodeBean.setMainCodeNum(mainCodeNum);
        batchBarCodeBean.setSubCodeNum(subCodeNum);
        batchBarCodeBean.setCustomerId(customerId);
        batchBarCodeBean.setAddTime(addTime);
        batchBarCodeBean.setPrinted(0);
        MallGoodBean goodBean = new MallGoodBean();
        goodBean.setGoodId(goodId);
        batchBarCodeBean.setGoodBean(goodBean);
        batchBarCodeDao.save(batchBarCodeBean);
        int index = 0;
        List<String> mainCodeArray = createMainCode(mainCodeNum);
        for (String mainCode : mainCodeArray) {
            //保存主码
            MainBarCodeBean mainBarCodeBean = new MainBarCodeBean();
            mainBarCodeBean.setMainCode(mainCode);
            mainBarCodeBean.setMainCodeImg(barCodeUnit.getBarCode(mainCode, 200, 50, customerId));
            mainBarCodeBean.setCustomerId(customerId);
            mainBarCodeBean.setAddTime(addTime);
            mainBarCodeBean.setGoodBean(goodBean);
            mainBarCodeBean.setSubCodeNum(subCodeNum);
            mainBarCodeBean.setBatchBarCodeBean(batchBarCodeBean);

            //生成副码
            List<String> subCodeArray = createSubCode(mainCode, subCodeNum);
            List<SubBarCodeBean> subBarCodeBeans = new ArrayList<SubBarCodeBean>();
            for (String subCode : subCodeArray) {
                //保存副码
                SubBarCodeBean subBarCodeBean = new SubBarCodeBean();
                subBarCodeBean.setAddTime(addTime);
                subBarCodeBean.setCustomerId(customerId);
                subBarCodeBean.setMainBar(mainBarCodeBean);
                subBarCodeBean.setSubCode(subCode);
                subBarCodeBean.setSubCodeImg(barCodeUnit.getBarCode(subCode, 200, 50, customerId));
                subBarCodeBeans.add(subBarCodeBean);
            }
            mainBarCodeBean.setSubBarCodeBeans(subBarCodeBeans);
            mainBarCodeDao.save(mainBarCodeBean);
            index++;
        }
        return index;
    }

    @Transactional
    public void batchDelete(long batchCodeId) throws IOException {
        //删除主码和副码
        List<MainBarCodeBean> mainBarCodeBeans = mainBarCodeDao.findByBatchCodeId(batchCodeId);
        for (MainBarCodeBean mainBarCodeBean : mainBarCodeBeans) {
            mainBarCodeDao.delete(mainBarCodeBean.getId());
            //删除条码图片
            resourceServer.deleteResource(mainBarCodeBean.getMainCodeImg());
            List<SubBarCodeBean> subBarCodeBeans = mainBarCodeBean.getSubBarCodeBeans();
            for (SubBarCodeBean subBarCodeBean : subBarCodeBeans) {
                resourceServer.deleteResource(subBarCodeBean.getSubCodeImg());
            }
        }
        //删除批次
        batchBarCodeDao.delete(batchCodeId);
    }

    @Transactional(readOnly = true)
    public BatchBarCodeBean findByBacthBarCodeId(long id) {
        return batchBarCodeDao.findOne(id);
    }

    @Transactional(readOnly = true)
    public MainBarCodeBean findMainBarCodeById(long id) {
        return mainBarCodeDao.findOne(id);
    }

    @Transactional(readOnly = true)
    public SubBarCodeBean findBySubBarCodeById(long id) {
        return subBarCodeDao.findOne(id);
    }

    @Transactional(readOnly = true)
    public Page<MainBarCodeBean> findMainBarCodeAll(long batchCodeId, int pageIndex, int pageSize) {
        return mainBarCodeDao.findAll(batchCodeId, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "id")));
    }

    @Transactional(readOnly = true)
    public List<MainBarCodeBean> findMainBarCodeAll(long batchCodeId) {
        return mainBarCodeDao.findByBatchCodeId(batchCodeId);
    }

    @Transactional(readOnly = true)
    public List<SubBarCodeBean> findSubBarCodeByMainCode(long mainBarCodeId) {
        MainBarCodeBean mainBarCodeBean = new MainBarCodeBean();
        mainBarCodeBean.setId(mainBarCodeId);
        return subBarCodeDao.findByMainBar(mainBarCodeBean);
    }

    @Transactional
    public void updatePrinted(long batchCodeId) {
        batchBarCodeDao.updatePrinted(batchCodeId);
    }

    @Transactional(readOnly = true)
    public Page<BatchBarCodeBean> findBatchBarCodeAll(int customerId, int goodId, int printed, int pageIndex, int pageSize) {
        return batchBarCodeDao.findAll(customerId, goodId, printed, new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "id")));
    }

    public void lockedByCode(String code) {
        if (code.length() == 15) {
            mainBarCodeDao.lockedByCode(code);
        }
        if (code.length() == 18) {
            subBarCodeDao.lockedByCode(code);
        }
    }

    public void unLockByCode(String code) {
        if (code.length() == 15) {
            mainBarCodeDao.unLockByCode(code);
        }
        if (code.length() == 18) {
            subBarCodeDao.unLockByCode(code);
        }
    }

    public boolean codeUsable(String code, int goodId) {
        int result = 0;
        if (code.length() == 15) {
            result = mainBarCodeDao.countByLocked(code, goodId);
        }
        if (code.length() == 18) {
            result = subBarCodeDao.countByLocked(code, goodId);
        }
        return result > 0;
    }

    /**
     * 生成主码
     *
     * @param mainCodeNum
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
                if (mainBarCodeDao.countByMainCode(buffer.toString()) == 0) {
                    mainCodeArray.add(buffer.toString());
                    break;
                }
            }
        }
        return mainCodeArray;
    }

    /**
     * 生成副码 主码开头
     *
     * @param mainCode
     * @param subCodeNum
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
