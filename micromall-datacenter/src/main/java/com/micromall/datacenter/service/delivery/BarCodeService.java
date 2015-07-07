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
     * 创建条码
     *
     * @param customerId  商家id
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
     * 根据id得到批次实体
     *
     * @param id
     * @return
     */
    BatchBarCodeBean findByBacthBarCodeId(long id);

    /**
     * 根据id得到主码实体
     *
     * @param id
     * @return
     */
    MainBarCodeBean findMainBarCodeById(long id);

    /**
     * 根据id得到副码实体
     *
     * @param id
     * @return
     */
    SubBarCodeBean findBySubBarCodeById(long id);

    /**
     * 得到主码列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<MainBarCodeBean> findMainBarCodeAll(long batchCodeId, int pageIndex, int pageSize);

    /**
     * 得到某批次的所有主码（用于打印）
     *
     * @param batchCodeId
     * @return
     */
    List<MainBarCodeBean> findMainBarCodeAll(long batchCodeId);

    /**
     * 根据主码id得到副码
     *
     * @param mainBarCodeId
     * @return
     */
    List<SubBarCodeBean> findSubBarCodeByMainCode(long mainBarCodeId);

    /**
     * 更新批次打印状态
     *
     * @param batchCodeId
     */
    void updatePrinted(long batchCodeId);

    /**
     * 得到批次列表（分组）
     *
     * @param customerId
     * @param goodId
     * @param printed
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<BatchBarCodeBean> findBatchBarCodeAll(int customerId, int goodId, int printed, int pageIndex, int pageSize);

    /**
     * 锁定条码
     *
     * @param code
     */
    void lockedByCode(String code);

    /**
     * 取消锁定条码
     *
     * @param code
     */
    void unLockByCode(String code);

    /**
     * 是否可用或者锁定
     *
     * @param code
     * @return true表示可用，false不可用
     */
    boolean codeUsable(String code, int goodId);
}
