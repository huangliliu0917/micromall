package com.micromall.datacenter.bean.delivery;

import com.micromall.datacenter.bean.goods.MallGoodBean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/29.
 */
@Entity
@Table(name = "Micromall_BacthBarCode")
public class BatchBarCodeBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name = "BatchCode")
    private String batchCode;
    @Column(name = "MainCodeNum")
    private int mainCodeNum;
    @Column(name = "SubCodeNum")
    private int subCodeNum;
    @Column(name = "AddTime")
    private Date addTime;
    @Column(name = "CustomerId")
    private int customerId;
    @ManyToOne
    @JoinColumn(name = "Good_Id")
    private MallGoodBean goodBean;
    /**
     * 是否打印，1表示已打印
     */
    @Column(name = "Printed")
    private int printed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public int getMainCodeNum() {
        return mainCodeNum;
    }

    public void setMainCodeNum(int mainCodeNum) {
        this.mainCodeNum = mainCodeNum;
    }

    public int getSubCodeNum() {
        return subCodeNum;
    }

    public void setSubCodeNum(int subCodeNum) {
        this.subCodeNum = subCodeNum;
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

    public MallGoodBean getGoodBean() {
        return goodBean;
    }

    public void setGoodBean(MallGoodBean goodBean) {
        this.goodBean = goodBean;
    }

    public int getPrinted() {
        return printed;
    }

    public void setPrinted(int printed) {
        this.printed = printed;
    }
}
