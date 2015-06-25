package com.micromall.adminWeb.controller.appApi;

import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.viewModel.appModel.IndexInfoModel;
import com.micromall.datacenter.viewModel.appModel.ManagerAppModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2015/6/24.
 */
public interface AppApiController {
    /**
     * 登录验证
     *
     * @param account  账号
     * @param password 密码
     * @return 返回null表示登录失败
     */
    @RequestMapping(method = RequestMethod.POST)
    ManagerAppModel login(String account, String password);

    /**
     * 订单列表
     *
     * @param customerId    商户id
     * @param searchKey     搜索关键字
     * @param deliverStatus 订单出货状态
     * @param pageIndex     页码
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    List<MallOrderBean> orderList(int customerId, @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey,
                                  @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
                                  @RequestParam(value = "deliverStatus", required = false, defaultValue = "-1") int deliverStatus);

    /**
     * 首页信息
     *
     * @param customerId
     * @return 今日订单总量，未出货总量，已出货总量
     */
    @RequestMapping(method = RequestMethod.GET)
    IndexInfoModel getIndexInfo(int customerId);

    /**
     * 订单详情
     *
     * @param orderId 订单id
     * @return 订单实体
     */
    MallOrderBean getOrderDetail(String orderId);

    /**
     * 出货
     *
     * @param orderId  订单id
     * @param proCodes 已逗号分隔的货品编号字符串
     * @return 1表示成功
     */
    int deliverPro(String orderId, String proCodes);

    /**
     * 修改密码
     *
     * @param managerId 管理员id
     * @param oldPass   旧密码
     * @param newPass   新密码
     * @return 1表示成功
     */
    int updatePass(int managerId, String oldPass, String newPass);
}
