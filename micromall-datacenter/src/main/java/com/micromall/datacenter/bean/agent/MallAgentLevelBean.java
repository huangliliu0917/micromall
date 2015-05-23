package com.micromall.datacenter.bean.agent;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/5/12.
 */
@Entity
@Table(name = "Micromall_AgentLevel")
public class MallAgentLevelBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Level_Id")
    private int levelId;
    @Column(name = "LevelName")
    private String levelName;
    @Column(name = "CustomerId")
    private int customerId;
    @Column(name = "SortNum")
    private int sortNum;

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }
}
