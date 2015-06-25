package com.micromall.datacenter.service.delivery;

import com.micromall.datacenter.bean.delivery.DeliverItemBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2015/6/23.
 */
public interface DeliverItemService {
    /**
     * ���������ʵ��
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
     * ���ϳ���
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
}
