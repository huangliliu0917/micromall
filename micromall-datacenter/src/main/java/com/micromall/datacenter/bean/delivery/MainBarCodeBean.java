package com.micromall.datacenter.bean.delivery;

import com.micromall.datacenter.bean.goods.MallGoodBean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
@Entity
@Table(name = "Micromall_BarCode")
public class MainBarCodeBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    /**
     * ����
     */
    @ManyToOne
    @JoinColumn(name = "BatchBarCode_Id")
    private BatchBarCodeBean batchBarCodeBean;
    /**
     * ���루15��ͷ��15λ)
     */
    @Column(name = "MainCode")
    private String mainCode;
    @Column(name = "MainCodeImg")
    private String mainCodeImg;
    @Column(name = "AddTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime;
    @ManyToOne
    @JoinColumn(name = "Good_Id")
    private MallGoodBean goodBean;
    private int customerId;
    @Column(name = "SubCodeNum")
    private int subCodeNum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mainBar", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SubBarCodeBean> subBarCodeBeans;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BatchBarCodeBean getBatchBarCodeBean() {
        return batchBarCodeBean;
    }

    public void setBatchBarCodeBean(BatchBarCodeBean batchBarCodeBean) {
        this.batchBarCodeBean = batchBarCodeBean;
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

    public int getSubCodeNum() {
        return subCodeNum;
    }

    public void setSubCodeNum(int subCodeNum) {
        this.subCodeNum = subCodeNum;
    }

    public List<SubBarCodeBean> getSubBarCodeBeans() {
        return subBarCodeBeans;
    }

    public void setSubBarCodeBeans(List<SubBarCodeBean> subBarCodeBeans) {
        this.subBarCodeBeans = subBarCodeBeans;
    }
}
