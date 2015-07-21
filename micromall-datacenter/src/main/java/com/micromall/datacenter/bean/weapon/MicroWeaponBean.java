package com.micromall.datacenter.bean.weapon;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/5/27.
 */
@Entity
@Table(name = "Mall_MicroWeapon")
public class MicroWeaponBean {
    @Id
    @Column(name = "Weapon_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int weaponId;
    @Column(name = "Weapon_Type")
    private int weaponType;
    @Column(name = "Weapon_Imgs", length = 2000)
    private String weaponImgs;
    @Lob
    @Column(name = "Weapon_Content")
    private String weaponContent;
    @Column(name = "CustomerId")
    private int customerId;
    @Column(name = "Weapon_Title")
    private String weaponTitle;

    @Transient
    private String[] imgList;

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public int getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(int weaponType) {
        this.weaponType = weaponType;
    }

    public String getWeaponImgs() {
        return weaponImgs;
    }

    public void setWeaponImgs(String weaponImgs) {
        this.weaponImgs = weaponImgs;
    }

    public String getWeaponContent() {
        return weaponContent;
    }

    public void setWeaponContent(String weaponContent) {
        this.weaponContent = weaponContent;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String[] getImgList() {
        return imgList;
    }

    public void setImgList(String[] imgList) {
        this.imgList = imgList;
    }

    public String getWeaponTitle() {
        return weaponTitle;
    }

    public void setWeaponTitle(String weaponTitle) {
        this.weaponTitle = weaponTitle;
    }
}
