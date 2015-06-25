package com.micromall.datacenter.service.order;

import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.bean.orders.MallOrderItemBean;
import com.micromall.datacenter.viewModel.order.MallOrderSearchViewModel;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2015/5/14.
 */
public interface MallOrderService {
    MallOrderBean create(MallOrderBean bean, int goodId, int realShipId);

    Page<MallOrderBean> findAll(MallOrderSearchViewModel searchViewModel, int pageIndex, int pageSize, int customerId);

    MallOrderBean findByOrderId(String orderId);

    void updateOrderStatus(String orderId, int orderStatus);

    void confirmShip(MallOrderBean orderBean, String[] proCodes, String logiName, String logiNum);

    void transferOrder(String orderId, int transferTo);

    String createOrderId(int customerId);

    Page<MallOrderBean> findAll(int customerId, int agentId, int pageIndex, int pageSize, int orderType, String orderId);

    int findCountInOrder(int customerId, int agentId);

    /**
     * ��Ʒ��ѯ
     *
     * @param customerId
     * @param snCode
     * @return
     */
    Page<MallOrderItemBean> findBySnCode(int customerId, String snCode, int pageIndex, int pageSize);

    /**
     * ���ó���״̬
     *
     * @param orderId
     * @param deliverStatus
     */
    void updateDeliver(String orderId, int deliverStatus);

    /**
     * �õ������б������������ѯ������app�ˣ�
     *
     * @param customerId    �̻�id
     * @param searchKey     �����ؼ��֣��ռ��ˣ��ռ����ֻ���
     * @param deliverStatus ����״̬
     * @param isToday       �Ƿ��ǽ��ն���
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<MallOrderBean> findOrderList(int customerId, String searchKey, int deliverStatus, int isToday, int pageIndex, int pageSize);

    /**
     * �õ�δ������ѳ��ⶩ������
     *
     * @param customerId
     * @param deliverStatus
     * @return
     */
    int countByDeliverStatus(int customerId, int deliverStatus);

    /**
     * �õ����ն�������
     *
     * @param customerId
     * @return
     */
    int countByTodayOrders(int customerId);
}
