package com.micromall.datacenter.viewModel.appModel;

/**
 * Created by Administrator on 2015/6/25.
 */
public class IndexInfoModel {
    private int todayOrderNum;
    private int undeliveredNum;
    private int deliveredNum;

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
}
