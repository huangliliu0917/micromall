package com.micromall.datacenter.pdaBean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/1.
 */
@Entity
@Table(name = "qty_info")
public class PdaGoodBean {
    @Id
    private String qcode;
    private String qname;
    private String qtip;
    @Column(name = "last_time")
    private Date lastName;
    private int state;
    private String formats;
    private String formula;
    private int packs;

    public String getQcode() {
        return qcode;
    }

    public void setQcode(String qcode) {
        this.qcode = qcode;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getQtip() {
        return qtip;
    }

    public void setQtip(String qtip) {
        this.qtip = qtip;
    }

    public Date getLastName() {
        return lastName;
    }

    public void setLastName(Date lastName) {
        this.lastName = lastName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public int getPacks() {
        return packs;
    }

    public void setPacks(int packs) {
        this.packs = packs;
    }
}
