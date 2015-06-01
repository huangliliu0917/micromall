package com.micromall.datacenter.bean.agent;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/5/28.
 */
@Entity
@Table(name = "Micromall_AgentApply")
public class MallAgentApplyBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Apply_Id")
    private int applyId;
    @Column(name = "Apply_Name")
    private String name;
    @Column(name = "Apply_Mobile")
    private String mobile;
    @Column(name = "Apply_Weixin")
    private String weixin;
    @Column(name = "Apply_Referrer")
    private String referrer;
    @Column(name = "Apply_CardId")
    private String cardId;
    @Column(name = "Apply_WorkOnTime")
    private String workOnTime;
    @Column(name = "Apply_SaleAmount")
    private String saleAmount;
    @Column(name = "Apply_WorkOnType")
    private String workOnType;
    @Column(name = "Apply_Area")
    private String area;
    @Column(name = "Apply_Time")
    private Date applyTime;
    @Column(name = "Apply_Status")
    private int applyStatus;
    @Column(name = "CustomerId")
    private int customerId;
    @Column(name = "RefuseReason")
    private String refuseReason;
    @Column(name = "Apply_LevelId")
    private int applyLevelId;
    @Column(name = "Apply_Reason")
    private String applyReason;
    @Transient
    private String applyLevelName;
    @ManyToOne
    @JoinColumn(name = "ResultLevelId")
    private MallAgentLevelBean resultLevel;
    @Column(name = "ResultReferrer")
    private String resultReferrer;
    @Column(name = "CardIdImg")
    private String cardIdImg;

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public int getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(int applyStatus) {
        this.applyStatus = applyStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getWorkOnTime() {
        return workOnTime;
    }

    public void setWorkOnTime(String workOnTime) {
        this.workOnTime = workOnTime;
    }

    public String getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(String saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getWorkOnType() {
        return workOnType;
    }

    public void setWorkOnType(String workOnType) {
        this.workOnType = workOnType;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public int getApplyLevelId() {
        return applyLevelId;
    }

    public void setApplyLevelId(int applyLevelId) {
        this.applyLevelId = applyLevelId;
    }

    public String getApplyLevelName() {
        return applyLevelName;
    }

    public void setApplyLevelName(String applyLevelName) {
        this.applyLevelName = applyLevelName;
    }

    public String getResultReferrer() {
        return resultReferrer;
    }

    public void setResultReferrer(String resultReferrer) {
        this.resultReferrer = resultReferrer;
    }

    public MallAgentLevelBean getResultLevel() {
        return resultLevel;
    }

    public void setResultLevel(MallAgentLevelBean resultLevel) {
        this.resultLevel = resultLevel;
    }

    public String getCardIdImg() {
        return cardIdImg;
    }

    public void setCardIdImg(String cardIdImg) {
        this.cardIdImg = cardIdImg;
    }
}
