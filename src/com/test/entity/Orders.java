package com.test.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2017/5/24.
 */
public class Orders implements Serializable {
    private String id;
    private String ordernum;//订单号
    private Integer num;//数量
    private Double price;//付款金额
    private Integer state; //订单状态  0-表示未发货 1-表示已发货

    private User user;
    private List<OrdersItem> items = new ArrayList<OrdersItem>();//订单中的订单项


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrdersItem> getItems() {
        return items;
    }

    public void setItems(List<OrdersItem> items) {
        this.items = items;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
