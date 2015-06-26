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
     * @param codeArray
     * @return
     */
    int batchDelete(String codeArray) throws IOException;

    /**
     * ͨ��id�õ�ʵ��
     *
     * @param id
     * @return
     */
    BarCodeBean findById(long id);

    /**
     * �����ѯ
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
