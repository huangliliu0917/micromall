package com.micromall.datacenter.bean.orders;

import com.micromall.datacenter.bean.goods.MallGoodBean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/5/29.
 */
@Entity
@Table(name = "Micromall_DeliverItem")
public class MallDeliverItemBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Sn")
    private String sn;
    @Column(name = "SendMobile")
    private String sendMobile;
    @Column(name = "AddTime")
    private Date addTime;
    @Column(name = "ProCode")
    private String proCode;
    @Column(name = "SnStatus")
    private int snStatus;
    @Column(name = "CustomerId")
    private int customerId;
    @Column(name = "SnType")
    private int snType;
    @ManyToOne
    @JoinColumn(name = "GoodId")
    private MallGoodBean goodBean;

    public int getSnType() {
        return snType;
    }

    public void setSnType(int snType) {
        this.snType = snType;
    }

    public MallGoodBean getGoodBean() {
        return goodBean;
    }

    public void setGoodBean(MallGoodBean goodBean) {
        this.goodBean = goodBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSendMobile() {
        return sendMobile;
    }

    public void setSendMobile(String sendMobile) {
        this.sendMobile = sendMobile;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public int getSnStatus() {
        return snStatus;
    }

    public void setSnStatus(int snStatus) {
        this.snStatus = snStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
