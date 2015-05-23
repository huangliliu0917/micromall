package com.micromall.datacenter.viewModel.agent;

import com.micromall.datacenter.bean.agent.MallAgentLevelBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2015/5/13.
 */
public class MallAgentSearchViewModel {
    private String agentName;
    private int agentLevel;
    private String agentAccount;
    private int customerId;
    private int agentStatus;
    //private int superAgentId;

//    public int getSuperAgentId() {
//        return superAgentId;
//    }
//
//    public void setSuperAgentId(int superAgentId) {
//        this.superAgentId = superAgentId;
//    }

    public int getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(int agentStatus) {
        this.agentStatus = agentStatus;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public int getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(int agentLevel) {
        this.agentLevel = agentLevel;
    }

    public String getAgentAccount() {
        return agentAccount;
    }

    public void setAgentAccount(String agentAccount) {
        this.agentAccount = agentAccount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
