package com.micromall.datacenter.bean.delivery;

import com.micromall.datacenter.bean.goods.MallGoodBean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/25.
 */
@Entity
@Table(name = "Micromall_BarCode")
public class BarCodeBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    /**
     * 主码（15开头共15位)
     */
    @Column(name = "MainCode")
    private String mainCode;
    @Column(name = "MainCodeImg")
    private String mainCodeImg;
    /**
     * 副码（主码开头共18位）
     */
    @Column(name = "SubCode")
    private String subCode;
    @Column(name = "SubCodeImg")
    private String subCodeImg;
    @Column(name = "AddTime")
    private Date addTime;
    @ManyToOne
    @JoinColumn(name = "Good_Id")
    private MallGoodBean goodBean;
    private int customerId;
    @Column(name = "Printed")
    private int printed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getMainCodeImg() {
        return mainCodeImg;
    }

    public void setMainCodeImg(String mainCodeImg) {
        this.mainCodeImg = mainCodeImg;
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

    public MallGoodBean getGoodBean() {
        return goodBean;
    }

    public void setGoodBean(MallGoodBean goodBean) {
        this.goodBean = goodBean;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPrinted() {
        return printed;
    }

    public void setPrinted(int printed) {
        this.printed = printed;
    }
}
