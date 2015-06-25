package com.micromall.datacenter.viewModel.appModel;

import com.micromall.datacenter.bean.orders.MallOrderItemBean;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/24.
 */
public class OrderInfoModel {
    private String orderId;
    private String orderName;
    private int proNum;
    private Date addTime;
    private String shipName;
    private String shipMobile;
    private int deliverStatus;
    private List<MallOrderItemBean> orderItems;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getProNum() {
        return proNum;
    }

    public void setProNum(int proNum) {
        this.proNum = proNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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

    public int getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(int deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public List<MallOrderItemBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<MallOrderItemBean> orderItems) {
        this.orderItems = orderItems;
    }
}
