package com.test.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/15.
 */
public class CartItem implements Serializable {
    private Book book;
    private Integer quantity; //小计数量
    private Double price; //小计价格

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return quantity * Double.parseDouble(book.getPrice());
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
