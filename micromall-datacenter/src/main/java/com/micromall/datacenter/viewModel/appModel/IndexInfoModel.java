package com.micromall.datacenter.viewModel.appModel;

/**
 * Created by Administrator on 2015/6/25.
 */
public class IndexInfoModel {
    private int todayOrderNum;
    private int undeliveredNum;
    private int deliveredNum;
    private String customerName;
    private String customerImg;

    public int getTodayOrderNum() {
        return todayOrderNum;
    }

    public void setTodayOrderNum(int todayOrderNum) {
        this.todayOrderNum = todayOrderNum;
    }

    public int getUndeliveredNum() {
        return undeliveredNum;
    }

    public void setUndeliveredNum(int undeliveredNum) {
        this.undeliveredNum = undeliveredNum;
    }

    public int getDeliveredNum() {
        return deliveredNum;
    }

    public void setDeliveredNum(int deliveredNum) {
        this.deliveredNum = deliveredNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }
}
