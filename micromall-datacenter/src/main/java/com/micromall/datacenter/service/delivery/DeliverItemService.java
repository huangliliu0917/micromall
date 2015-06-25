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
     * 保存出货单实体
     *
     * @param bean
     * @return
     */
    DeliverItemBean save(DeliverItemBean bean);

    /**
     * 出库列表（分页带条件查询）
     *
     * @param customerId 商户id
     * @param managerKey 出库管理员关键字
     * @param shipKey    收件人关键字
     * @param beginTime  开始时间
     * @param endTime    结束时间
     * @param pageIndex  页码
     * @param pageSize   每页数据量
     * @return
     */
    Page<DeliverItemBean> findAll(int customerId, String managerKey, String shipKey, String beginTime, String endTime, int pageIndex, int pageSize);

    /**
     * 得到出库实体
     *
     * @param orderId 订单id
     * @return
     */
    DeliverItemBean findByOrderId(String orderId);

    /**
     * 作废出货
     *
     * @param id
     */
    void setInvalidate(int id);

    /**
     * 设置状态
     *
     * @param orderId
     */
    void setDelivered(String orderId);
}
