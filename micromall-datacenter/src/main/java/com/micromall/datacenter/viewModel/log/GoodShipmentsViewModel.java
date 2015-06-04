package com.micromall.datacenter.viewModel.log;

/**
 * Created by Administrator on 2015/6/4.
 */
public class GoodShipmentsViewModel {
    private String goodName;
    private double price;
    private long num;
    private String goodCode;

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public GoodShipmentsViewModel(String goodName, double price, long num, String goodCode) {
        this.goodName = goodName;
        this.price = price;
        this.num = num;
        this.goodCode = goodCode;
    }
}
