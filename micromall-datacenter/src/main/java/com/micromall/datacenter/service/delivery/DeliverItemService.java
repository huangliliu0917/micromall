package com.micromall.datacenter.service.delivery;

import com.micromall.datacenter.bean.delivery.DeliverItemBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/23.
 */
public interface DeliverItemService {
    /**
     * 保存
     *
     * @param bean
     * @return
     */
    DeliverItemBean save(DeliverItemBean bean);

    /**
     * �����б���ҳ��������ѯ��
     *
     * @param customerId �̻�id
     * @param managerKey �������Ա�ؼ���
     * @param shipKey    �ռ��˹ؼ���
     * @param beginTime  ��ʼʱ��
     * @param endTime    ����ʱ��
     * @param pageIndex  ҳ��
     * @param pageSize   ÿҳ������
     * @return
     */
    Page<DeliverItemBean> findAll(int customerId, String managerKey, String shipKey, String beginTime, String endTime, int pageIndex, int pageSize);

    /**
     * �õ�����ʵ��
     *
     * @param orderId ����id
     * @return
     */
    DeliverItemBean findByOrderId(String orderId);

    /**
     * 设置为已失效，并将条码重新解锁
     *
     * @param id
     */
    void setInvalidate(int id);

    /**
     * ����״̬
     *
     * @param orderId
     */
    void setDelivered(String orderId);

    /**
     * ����
     *
     * @param customerId �̻�id
     * @param proCodes   ���ŷָ��Ļ�Ʒ����ַ���
     * @param orderId    ����id
     * @param logiName   ��ݹ�˾
     * @param logiNum    ��ݵ���
     * @param managerId  ������id
     */
    Map<String, Integer> deliverPro(int customerId, String proCodes, String orderId, String logiName, String logiNum, int managerId);
}
