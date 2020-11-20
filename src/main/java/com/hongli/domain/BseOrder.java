package com.hongli.domain;

import javax.persistence.*;

@Entity
@Table(name = "bse_order")
public class BseOrder {
    @Id
    private int id;

    @Column(name ="order_num")
    private String orderNum;

    @Transient
    private String record;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getRecord() {
        return "ID is ===> " + this.getId() + "; Order Number is ===>" + this.getOrderNum();
    }
}
