package com.micromall.datacenter.viewModel.log;

import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2015/6/4.
 */
public class AgentShipmentsViewModel {
    private String agentMobile;
    private String agentName;
    private long proNum;
    private String levelName;
    private String authorizationCode;

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getAgentMobile() {
        return agentMobile;
    }

    public void setAgentMobile(String agentMobile) {
        this.agentMobile = agentMobile;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public long getProNum() {
        return proNum;
    }

    public void setProNum(long proNum) {
        this.proNum = proNum;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public AgentShipmentsViewModel(String agentMobile, String agentName, long proNum, String levelName, String authorizationCode) {
        this.agentMobile = agentMobile;
        this.agentName = agentName;
        this.proNum = proNum;
        this.levelName = levelName;
        this.authorizationCode = authorizationCode;
    }

    public AgentShipmentsViewModel() {
    }
}
