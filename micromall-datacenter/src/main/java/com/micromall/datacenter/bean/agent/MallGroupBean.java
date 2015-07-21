package com.micromall.datacenter.bean.agent;

import javax.persistence.*;

/**
 * 分组
 * Created by allan on 2015/7/21.
 */
@Entity
@Table(name = "Micromall_Group")
public class MallGroupBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Group_Id")
    private int groupId;
    @Column(name = "Group_Name")
    private String groupName;
    @Column(name = "Group_Desc")
    private String groupDesc;
    @Column(name = "CustomerId", updatable = false)
    private int customerId;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
