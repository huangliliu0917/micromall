package com.micromall.datacenter.bean.orders;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/5/14.
 */
@Entity
@Table(name = "Micromall_OrderItems")
public class MallOrderItemBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Item_Id")
    private int itemId;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "Order_Id")
    private MallOrderBean ordersBean;

    @Column(name = "Pro_Code")
    private String proCode;
    @Column(name = "CustomerId")
    private int customerId;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public MallOrderBean getOrdersBean() {
        return ordersBean;
    }

    public void setOrdersBean(MallOrderBean ordersBean) {
        this.ordersBean = ordersBean;
    }
}
