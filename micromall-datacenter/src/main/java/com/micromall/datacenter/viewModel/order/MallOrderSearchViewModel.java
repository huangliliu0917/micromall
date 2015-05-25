package com.micromall.datacenter.viewModel.order;

import com.micromall.datacenter.bean.agent.MallAgentBean;

import java.util.Date;

/**
 * Created by Administrator on 2015/5/14.
 */
public class MallOrderSearchViewModel {
    private String orderId;
    private String beginTime;
    private String endTime;
    private int orderStatus;
    private String shipName;
    private String shipMobile;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipMobile() {
        return shipMobile;
    }

    public void setShipMobile(String shipMobile) {
        this.shipMobile = shipMobile;
    }
}
