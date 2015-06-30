package com.micromall.datacenter.bean.delivery;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/27.
 */
@Entity
@Table(name = "Micromall_SubBarCode")
public class SubBarCodeBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    /**
     * 主码id
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "MainBarCodeId")
    private MainBarCodeBean mainBar;
    /**
     * 副码（主码开头共18位）
     */
    @Column(name = "SubCode")
    private String subCode;
    @Column(name = "SubCodeImg")
    private String subCodeImg;
    @Column(name = "AddTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime;
    @Column(name = "CustomerId")
    private int customerId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MainBarCodeBean getMainBar() {
        return mainBar;
    }

    public void setMainBar(MainBarCodeBean mainBar) {
        this.mainBar = mainBar;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubCodeImg() {
        return subCodeImg;
    }

    public void setSubCodeImg(String subCodeImg) {
        this.subCodeImg = subCodeImg;
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
}
