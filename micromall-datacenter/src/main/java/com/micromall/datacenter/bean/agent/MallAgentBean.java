package com.micromall.datacenter.bean.agent;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/5/12.
 */
@Entity
@Table(name = "Micromall_Agent")
public class MallAgentBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Agent_Id")
    private int agentId;

    @Column(name = "Agent_Name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "Agent_Level")
    private MallAgentLevelBean agentLevel;
    @Column(name = "Agent_Account")
    private String agentAccount;
    @Column(name = "Agent_Password")
    private String agentPassword;
    @Column(name = "Agent_P_Agent")
    private int superAgentId;

    @Column(name = "Agent_Area")
    private String agentArea;
    @Column(name = "Agent_Channel")
    private String agentChannel;
    @Column(name = "Agent_CardId")
    private String agentCardId;
    @Column(name = "Agent_QQ")
    private String agentQQ;
    @Column(name = "Agent_Weixin")
    private String agentWeixin;
    @Column(name = "Agent_Addr")
    private String agentAddr;
    @Column(name = "Add_Time")
    @Temporal(TemporalType.DATE)
    private Date addTime;
    @Column(name = "IsDelete")
    private int isDelete;
    @Column(name = "CustomerId")
    private int customerId;
    @Column(name = "Ship_Addr")
    private String shipAddr;
    @Column(name = "Agent_Status")
    private int agentStatus;
    @Column(name = "Agent_Certificate")
    private String agentCertificate;

    public String getAgentAccount() {
        return agentAccount;
    }

    public void setAgentAccount(String agentAccount) {
        this.agentAccount = agentAccount;
    }

    public String getAgentPassword() {
        return agentPassword;
    }

    public void setAgentPassword(String agentPassword) {
        this.agentPassword = agentPassword;
    }

    public int getSuperAgentId() {
        return superAgentId;
    }

    public void setSuperAgentId(int superAgentId) {
        this.superAgentId = superAgentId;
    }

    public String getAgentArea() {
        return agentArea;
    }

    public void setAgentArea(String agentArea) {
        this.agentArea = agentArea;
    }

    public String getAgentChannel() {
        return agentChannel;
    }

    public void setAgentChannel(String agentChannel) {
        this.agentChannel = agentChannel;
    }

    public String getAgentCardId() {
        return agentCardId;
    }

    public void setAgentCardId(String agentCardId) {
        this.agentCardId = agentCardId;
    }

    public String getAgentQQ() {
        return agentQQ;
    }

    public void setAgentQQ(String agentQQ) {
        this.agentQQ = agentQQ;
    }

    public String getAgentWeixin() {
        return agentWeixin;
    }

    public void setAgentWeixin(String agentWeixin) {
        this.agentWeixin = agentWeixin;
    }

    public String getAgentAddr() {
        return agentAddr;
    }

    public void setAgentAddr(String agentAddr) {
        this.agentAddr = agentAddr;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getShipAddr() {
        return shipAddr;
    }

    public void setShipAddr(String shipAddr) {
        this.shipAddr = shipAddr;
    }

    public int getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(int agentStatus) {
        this.agentStatus = agentStatus;
    }

    public String getAgentCertificate() {
        return agentCertificate;
    }

    public void setAgentCertificate(String agentCertificate) {
        this.agentCertificate = agentCertificate;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MallAgentLevelBean getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(MallAgentLevelBean agentLevel) {
        this.agentLevel = agentLevel;
    }
}
