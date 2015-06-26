package com.micromall.datacenter.service.delivery;

import com.google.zxing.WriterException;
import com.micromall.datacenter.bean.delivery.BarCodeBean;
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
     * @param codeArray
     * @return
     */
    int batchDelete(String codeArray) throws IOException;

    /**
     * 通过id得到实体
     *
     * @param id
     * @return
     */
    BarCodeBean findById(long id);

    /**
     * 分组查询
     *
     * @param customerId
     * @param goodId
     * @param printed
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<BarCodeBean> findAll(int customerId, int goodId, int printed, int pageIndex, int pageSize);
}
