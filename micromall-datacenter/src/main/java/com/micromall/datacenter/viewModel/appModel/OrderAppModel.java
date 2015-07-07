package com.micromall.datacenter.viewModel.appModel;

import com.micromall.datacenter.bean.orders.MallOrderItemBean;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/24.
 */
public class OrderAppModel {
    private String orderId;
    private String orderName;
    private String orderImg;
    private int proNum;
    private Date addTime;
    private String shipName;
    private String shipMobile;
    private String shipAddr;
    private int deliverStatus;
    private List<String> orderItems;
    private String logiName;
    private String logiNum;

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

    public String getOrderImg() {
        return orderImg;
    }

    public void setOrderImg(String orderImg) {
        this.orderImg = orderImg;
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

    public String getShipAddr() {
        return shipAddr;
    }

    public void setShipAddr(String shipAddr) {
        this.shipAddr = shipAddr;
    }

    public int getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(int deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public List<String> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<String> orderItems) {
        this.orderItems = orderItems;
    }

    public String getLogiName() {
        return logiName;
    }

    public void setLogiName(String logiName) {
        this.logiName = logiName;
    }

    public String getLogiNum() {
        return logiNum;
    }

    public void setLogiNum(String logiNum) {
        this.logiNum = logiNum;
    }
}
