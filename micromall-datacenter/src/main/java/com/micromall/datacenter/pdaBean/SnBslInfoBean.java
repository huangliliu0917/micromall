package com.micromall.datacenter.pdaBean;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/6/1.
 */
@Entity
@Table(name = "sn_bsl_info")
public class SnBslInfoBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snbsl00")
    private int id;
    private String snbsl01;
    @Column(name = "snbsl02")
    private String mainCode;
    @Column(name = "snbsl03")
    private String subsidiaryCode;
    private String snbsl04;
    private String snbsl05;
    private String snbsl06;
    private String snbsl07;
    private String snbsl08;
    private String snbsl09;
    private String snbsl10;

    public String getSnbsl10() {
        return snbsl10;
    }

    public void setSnbsl10(String snbsl10) {
        this.snbsl10 = snbsl10;
    }

    public String getSnbsl09() {
        return snbsl09;
    }

    public void setSnbsl09(String snbsl09) {
        this.snbsl09 = snbsl09;
    }

    public String getSnbsl08() {
        return snbsl08;
    }

    public void setSnbsl08(String snbsl08) {
        this.snbsl08 = snbsl08;
    }

    public String getSnbsl07() {
        return snbsl07;
    }

    public void setSnbsl07(String snbsl07) {
        this.snbsl07 = snbsl07;
    }

    public String getSnbsl06() {
        return snbsl06;
    }

    public void setSnbsl06(String snbsl06) {
        this.snbsl06 = snbsl06;
    }

    public String getSnbsl05() {
        return snbsl05;
    }

    public void setSnbsl05(String snbsl05) {
        this.snbsl05 = snbsl05;
    }

    public String getSnbsl04() {
        return snbsl04;
    }

    public void setSnbsl04(String snbsl04) {
        this.snbsl04 = snbsl04;
    }

    public String getSubsidiaryCode() {
        return subsidiaryCode;
    }

    public void setSubsidiaryCode(String subsidiaryCode) {
        this.subsidiaryCode = subsidiaryCode;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getSnbsl01() {
        return snbsl01;
    }

    public void setSnbsl01(String snbsl01) {
        this.snbsl01 = snbsl01;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
