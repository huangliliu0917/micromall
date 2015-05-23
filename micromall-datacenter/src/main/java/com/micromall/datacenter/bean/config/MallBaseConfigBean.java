package com.micromall.datacenter.bean.config;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/5/12.
 */

@Entity
@Table(name = "Micromall_BaseConfig")
public class MallBaseConfigBean {
    @Id
    @Column(name = "CustomerId")
    private int customerId;

    @Column(name = "Mall_Logo")
    private String logo;

    @Column(name = "Mall_Title")
    private String title;

    @Column(name = "Mall_Contact")
    private String contact;

    @Column(name = "Mall_AboutUs")
    private String aboutUs;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }
}
