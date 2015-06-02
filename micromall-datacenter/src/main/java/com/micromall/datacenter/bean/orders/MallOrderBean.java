package com.micromall.datacenter.bean.orders;

import com.micromall.datacenter.bean.goods.MallGoodBean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/5/14.
 */
@Entity
@Table(name = "Micromall_Orders")
public class MallOrderBean {
    @Id
    @Column(name = "Order_Id")
    private String orderId;
    @Column(name = "Order_Name")
    private String orderName;
    @Column(name = "Order_Img")
    private String orderImg;
    @Column(name = "Ship_Name")
    private String shipName;
    @Column(name = "Ship_Mobile")
    private String shipMobile;
    @Column(name = "Ship_Addr")
    private String shipAddr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordersBean", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<MallOrderItemBean> orderItems;
    @Column(name = "TotalPrice")
    private double totalPrice;
    @Column(name = "Order_Desc")
    private String orderDesc;
    @Column(name = "CustomerId")
    private int customerId;
    @ManyToOne
    @JoinColumn(name = "Good_Id")
    private MallGoodBean good;
    @Column(name = "Order_Status")
    private int orderStatus;
    @Column(name = "Owner_Id")
    private int ownerId;
    @Column(name = "Send_Id")
    private int sendId;
    @Column(name = "RealShip_Id")
    private int realShipId;
    @Column(name = "Ship_Info")
    private String shipInfo;
    @Column(name = "DeliverPath")
    private String deliverPath;
    @Column(name = "Add_Time")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date addTime;
    @Column(name = "Pro_Num")
    private int proNum;
    @Column(name = "Logi_Name")
    private String logiName;
    @Column(name = "Logi_Num")
    private String logiNum;

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

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

    public Set<MallOrderItemBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<MallOrderItemBean> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public MallGoodBean getGood() {
        return good;
    }

    public void setGood(MallGoodBean good) {
        this.good = good;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public int getRealShipId() {
        return realShipId;
    }

    public void setRealShipId(int realShipId) {
        this.realShipId = realShipId;
    }

    public String getShipInfo() {
        return shipInfo;
    }

    public void setShipInfo(String shipInfo) {
        this.shipInfo = shipInfo;
    }

    public String getDeliverPath() {
        return deliverPath;
    }

    public void setDeliverPath(String deliverPath) {
        this.deliverPath = deliverPath;
    }

    public int getProNum() {
        return proNum;
    }

    public void setProNum(int proNum) {
        this.proNum = proNum;
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
