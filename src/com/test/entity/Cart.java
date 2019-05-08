package com.test.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15.
 */
public class Cart implements Serializable {
    //购物车中的购物项
    //key是购物项对应的book的id， value就是购物项
    private Map<String, CartItem> items = new HashMap<String, CartItem>();
    private Integer amount;//购物总数量
    private Double totalPrice; //总价格

    public Map<String, CartItem> getItems() {
        return items;
    }

    public Integer getAmount() {
        amount = 0;
        for(Map.Entry<String, CartItem> item: items.entrySet()){
            amount += item.getValue().getQuantity();
        }
        return amount;
    }

//    public void setAmount(Integer amount) {
//        this.amount = amount;
//    }

    public Double getTotalPrice() {
        totalPrice = 0.0;
        for(Map.Entry<String, CartItem> item: items.entrySet()){
            totalPrice += item.getValue().getPrice();
        }
        return totalPrice;
    }

    public void addBook(Book book) {
        if(items.containsKey(book.getId())){
            CartItem item = items.get(book.getId());
            item.setQuantity(item.getQuantity()+1);
        }else{
            CartItem item = new CartItem();
            item.setBook(book);
            item.setQuantity(1);
            items.put(book.getId(), item);
        }
    }

//    public void setTotalPrice(Float totalPrice) {
//        this.totalPrice = totalPrice;
//    }
}
