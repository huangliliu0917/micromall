package com.micromall.datacenter.service.delivery;

import com.google.zxing.WriterException;
import com.micromall.datacenter.bean.delivery.BatchBarCodeBean;
import com.micromall.datacenter.bean.delivery.MainBarCodeBean;
import com.micromall.datacenter.bean.delivery.SubBarCodeBean;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
public interface BarCodeService {
    /**
     * 创建并保存条码
     *
     * @param customerId  商户id
     * @param mainCodeNum 主码数量
     * @param subCodeNum  副码数量
     * @param goodId      商品id
     * @return
     */
    int createBarCode(int customerId, int mainCodeNum, int subCodeNum, int goodId) throws IOException, WriterException;

    /**
     * 批量删除
     *
     * @param batchCodeId
     * @return
     */
    void batchDelete(long batchCodeId) throws IOException;

    /**
     * 通过id得到主码实体
     *
     * @param id
     * @return
     */
    MainBarCodeBean findMainBarCodeById(long id);

    /**
     * 通过id得到副码实体
     *
     * @param id
     * @return
     */
    SubBarCodeBean findBySubBarCodeById(long id);

    /**
     * 某批次的主码分组查询
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<MainBarCodeBean> findMainBarCodeAll(long batchCodeId, int pageIndex, int pageSize);

    /**
     * 得到批次的所有主码，用于打印
     *
     * @param batchCodeId
     * @return
     */
    List<MainBarCodeBean> findByBatchCodeId(long batchCodeId);

    /**
     * 某个主码下的副码
     *
     * @param mainBarCodeId
     * @return
     */
    List<SubBarCodeBean> findSubBarCodeByMainCode(long mainBarCodeId);

    /**
     * 修改批次为已打印
     *
     * @param batchCodeId
     */
    void updatePrinted(int batchCodeId);

    Page<BatchBarCodeBean> findBatchBarCodeAll(int customerId, int goodId, int printed, int pageIndex, int pageSize);
}
