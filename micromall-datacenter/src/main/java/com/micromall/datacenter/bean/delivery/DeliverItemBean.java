package com.micromall.datacenter.bean.delivery;

import com.mchange.v1.cachedstore.CachedStore;
import com.micromall.datacenter.bean.goods.MallGoodBean;
import com.micromall.datacenter.bean.orders.MallOrderBean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/23.
 */
@Entity
@Table(name = "Micromall_DeliverItems")
public class DeliverItemBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "Order_Id")
    private MallOrderBean orderBean;
    @ManyToOne
    @JoinColumn(name = "Manager_Id")
    private ManagerBean managerBean;
    @Column(name = "Pro_Code", length = 2000)
    private String proCode;
    @Column(name = "Add_Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime;
    @Column(name = "CustomerId")
    private int customerId;
    @Column(name = "Logi_Name")
    private String logiName;
    @Column(name = "Logi_Num")
    private String logiNum;
    /**
     * 状态，0表示待发货，1表示作废，2表示已发货
     */
    @Column(name = "DeliverStatus")
    private int deliverStatus;
    @Transient
    private String[] proList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MallOrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(MallOrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public ManagerBean getManagerBean() {
        return managerBean;
    }

    public void setManagerBean(ManagerBean managerBean) {
        this.managerBean = managerBean;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String[] getProList() {
        return proList;
    }

    public void setProList(String[] proList) {
        this.proList = proList;
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

    public int getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(int deliverStatus) {
        this.deliverStatus = deliverStatus;
    }
}
