package com.micromall.adminWeb.controller.appApi;

import com.micromall.adminWeb.bean.ApiResult;
import com.micromall.datacenter.bean.orders.MallOrderBean;
import com.micromall.datacenter.viewModel.appModel.IndexInfoModel;
import com.micromall.datacenter.viewModel.appModel.ManagerAppModel;
import com.micromall.datacenter.viewModel.appModel.OrderAppModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/24.
 */
public interface AppApiController {
    /**
     * ��¼��֤
     *
     * @param account  �˺�
     * @param password ����
     * @return ����null��ʾ��¼ʧ��
     */
    @RequestMapping(method = RequestMethod.POST)
    ApiResult<ManagerAppModel> login(String account, String password);

    /**
     * �����б�
     *
     * @param customerId    �̻�id
     * @param searchKey     �����ؼ���
     * @param deliverStatus ��������״̬
     * @param pageIndex     ҳ��
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    ApiResult<List<OrderAppModel>> orderList(int customerId, @RequestParam(value = "searchKey", required = false, defaultValue = "") String searchKey,
                                             @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex, int pageSize,
                                             @RequestParam(value = "isToday", required = false, defaultValue = "0") int isToday,
                                             @RequestParam(value = "deliverStatus", required = false, defaultValue = "-1") int deliverStatus);

    /**
     * ��ҳ��Ϣ
     *
     * @param customerId
     * @return ���ն���������δ�����������ѳ�������
     */
    @RequestMapping(method = RequestMethod.GET)
    ApiResult<IndexInfoModel> getIndexInfo(int customerId);

    /**
     * ��������
     *
     * @param orderId ����id
     * @return ����ʵ��
     */
    ApiResult<OrderAppModel> getOrderDetail(String orderId);

    /**
     * ����
     *
     * @param orderId  ����id
     * @param proCodes �Ѷ��ŷָ��Ļ�Ʒ����ַ���
     * @return 1��ʾ�ɹ�
     */
    ApiResult<Map<String, Integer>> deliverPro(String orderId, String proCodes, int customerId, int managerId,
                                               @RequestParam(value = "logiName", required = false, defaultValue = "") String logiName,
                                               @RequestParam(value = "logiNum", required = false, defaultValue = "") String logiNum);

    /**
     * �޸�����
     *
     * @param managerId ����Աid
     * @param oldPass   ������
     * @param newPass   ������
     * @return 1��ʾ�ɹ�
     */
    ApiResult<Integer> updatePass(int managerId, String oldPass, String newPass);
}
