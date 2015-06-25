package com.micromall.datacenter.bean.delivery;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/23.
 */
@Entity
@Table(name = "Micromall_Manager")
public class ManagerBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "M_Account", updatable = false)
    private String account;
    @Column(name = "M_Password")
    private String password;
    @Column(name = "M_Name")
    private String name;
    @Column(name = "M_AddTime", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime;
    @Column(name = "CustomerId", updatable = false)
    private int customerId;
    @Column(name = "M_IsDelete")
    private int isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
