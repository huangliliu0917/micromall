package com.micromall.datacenter.viewModel.agent;

import com.micromall.datacenter.bean.agent.MallAgentBean;

/**
 * Created by Administrator on 2015/5/13.
 */
public class MallUserSearchViewModel {
    private String userMobile;
    private String name;
    private int customerId;
    private MallAgentBean agent;

    public MallAgentBean getAgent() {
        return agent;
    }

    public void setAgent(MallAgentBean agent) {
        this.agent = agent;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
