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
     * 货品查询
     *
     * @param customerId
     * @param snCode
     * @return
     */
    Page<MallOrderItemBean> findBySnCode(int customerId, String snCode, int pageIndex, int pageSize);

    /**
     * 设置出货状态
     *
     * @param orderId
     * @param deliverStatus
     */
    void updateDeliver(String orderId, int deliverStatus);

    /**
     * 得到订单列表（分组带条件查询，用于app端）
     *
     * @param customerId    商户id
     * @param searchKey     搜索关键字（收件人，收件人手机）
     * @param deliverStatus 出货状态
     * @param isToday       是否是今日订单
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<MallOrderBean> findOrderList(int customerId, String searchKey, int deliverStatus, int isToday, int pageIndex, int pageSize);

    /**
     * 得到未出库或已出库订单数量
     *
     * @param customerId
     * @param deliverStatus
     * @return
     */
    int countByDeliverStatus(int customerId, int deliverStatus);

    /**
     * 得到今日订单数量
     *
     * @param customerId
     * @return
     */
    int countByTodayOrders(int customerId);
}
