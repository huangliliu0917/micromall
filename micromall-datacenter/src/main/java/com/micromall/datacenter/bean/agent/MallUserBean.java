package com.micromall.datacenter.bean.agent;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/5/13.
 */
@Entity
@Table(name = "Micromall_User")
public class MallUserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private int userId;
    @Column(name = "User_Mobile")
    private String userMobile;
    @Column(name = "User_Name")
    private String userName;
    @Column(name = "User_QQ")
    private String userQQ;
    @Column(name = "User_Weixin")
    private String userWeixin;
    @Column(name = "User_Addr")
    private String userAddr;
    @ManyToOne
    @JoinColumn(name = "Agent_Id")
    private MallAgentBean agent;
    @Column(name = "CustomerId")
    private int customerId;
    @Column(name = "IsDelete")
    private int isDelete;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserQQ() {
        return userQQ;
    }

    public void setUserQQ(String userQQ) {
        this.userQQ = userQQ;
    }

    public String getUserWeixin() {
        return userWeixin;
    }

    public void setUserWeixin(String userWeixin) {
        this.userWeixin = userWeixin;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public MallAgentBean getAgent() {
        return agent;
    }

    public void setAgent(MallAgentBean agent) {
        this.agent = agent;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
