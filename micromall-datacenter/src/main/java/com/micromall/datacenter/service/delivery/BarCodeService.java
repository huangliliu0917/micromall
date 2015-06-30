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
     * ��������������
     *
     * @param customerId  �̻�id
     * @param mainCodeNum ��������
     * @param subCodeNum  ��������
     * @param goodId      ��Ʒid
     * @return
     */
    int createBarCode(int customerId, int mainCodeNum, int subCodeNum, int goodId) throws IOException, WriterException;

    /**
     * ����ɾ��
     *
     * @param batchCodeId
     * @return
     */
    void batchDelete(long batchCodeId) throws IOException;

    /**
     * ͨ��id�õ�����ʵ��
     *
     * @param id
     * @return
     */
    MainBarCodeBean findMainBarCodeById(long id);

    /**
     * ͨ��id�õ�����ʵ��
     *
     * @param id
     * @return
     */
    SubBarCodeBean findBySubBarCodeById(long id);

    /**
     * ĳ���ε���������ѯ
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<MainBarCodeBean> findMainBarCodeAll(long batchCodeId, int pageIndex, int pageSize);

    /**
     * �õ����ε��������룬���ڴ�ӡ
     *
     * @param batchCodeId
     * @return
     */
    List<MainBarCodeBean> findByBatchCodeId(long batchCodeId);

    /**
     * ĳ�������µĸ���
     *
     * @param mainBarCodeId
     * @return
     */
    List<SubBarCodeBean> findSubBarCodeByMainCode(long mainBarCodeId);

    /**
     * �޸�����Ϊ�Ѵ�ӡ
     *
     * @param batchCodeId
     */
    void updatePrinted(int batchCodeId);

    Page<BatchBarCodeBean> findBatchBarCodeAll(int customerId, int goodId, int printed, int pageIndex, int pageSize);
}
