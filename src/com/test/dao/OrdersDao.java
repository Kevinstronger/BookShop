package com.test.dao;

import com.test.entity.Orders;
import com.test.entity.User;

import java.util.List;

/**
 * Created by kevin on 2017/5/25.
 */
public interface OrdersDao {
    public void addOrders(Orders orders, User user);

    List<Orders> findOrdersByUserId(String id);

    List<Orders> findOrdersByState(int state);

    Orders findOrdersById(String ordersId);

    void processOrders(String ordersId);
}
