package com.micromall.datacenter.pdaBean;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/5/29.
 */
@Entity
@Table(name = "sn_info")
public class SnInfoBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;
    private Date stime;
    private Date dtime;
    private String qcode;
    private String gno;
    private String sn;
    private String stype;
    private String oper_no;
    private String tip;
    private String bigsmall;
    private Date vtime;
    private int snStatus;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public String getQcode() {
        return qcode;
    }

    public void setQcode(String qcode) {
        this.qcode = qcode;
    }

    public String getGno() {
        return gno;
    }

    public void setGno(String gno) {
        this.gno = gno;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getOper_no() {
        return oper_no;
    }

    public void setOper_no(String oper_no) {
        this.oper_no = oper_no;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getBigsmall() {
        return bigsmall;
    }

    public void setBigsmall(String bigsmall) {
        this.bigsmall = bigsmall;
    }

    public Date getVtime() {
        return vtime;
    }

    public void setVtime(Date vtime) {
        this.vtime = vtime;
    }

    public int getSnStatus() {
        return snStatus;
    }

    public void setSnStatus(int snStatus) {
        this.snStatus = snStatus;
    }
}
