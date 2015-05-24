package com.micromall.datacenter.bean.goods;

import com.micromall.datacenter.viewModel.good.GoodPriceViewModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/5/12.
 */
@Entity
@Table(name = "Micromall_Goods")
public class MallGoodBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Good_Id")
    private int goodId;

    @Column(name = "Good_Name")
    private String goodName;
    @Column(name = "Good_Code")
    private String goodCode;
    @Column(name = "Good_PriceInfo")
    private String priceInfo;
    @Column(name = "Good_Desc")
    private String goodDesc;
    @Column(name = "AddTime")
    @Temporal(TemporalType.DATE)
    private Date addTime;
    @Column(name = "IsDelete")
    private int isDelete;
    @Column(name = "Good_Img")
    private String goodImg;
    @Column(name = "Price")
    private double price;
    @Column(name = "CustomerId")
    private int customerId;

    @Transient
    private List<GoodPriceViewModel> priceViewModelList;

    public List<GoodPriceViewModel> getPriceViewModelList() {
        return priceViewModelList;
    }

    public void setPriceViewModelList(List<GoodPriceViewModel> priceViewModelList) {
        this.priceViewModelList = priceViewModelList;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodCode() {
        return goodCode;
    }

    public void setGoodCode(String goodCode) {
        this.goodCode = goodCode;
    }

    public String getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    public String getGoodDesc() {
        return goodDesc;
    }

    public void setGoodDesc(String goodDesc) {
        this.goodDesc = goodDesc;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
