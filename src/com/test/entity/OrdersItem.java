package com.test.entity;

import java.io.Serializable;

/**
 * Created by kevin on 2017/5/25.
 */
public class OrdersItem implements Serializable {
    private String id;
    private Integer num;
    private Float price;
    private Book book;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
