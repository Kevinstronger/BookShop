package com.test.test;

import com.test.dao.OrdersDao;
import com.test.dao.impl.OrdersDaoImpl;
import com.test.entity.Orders;
import com.test.entity.OrdersItem;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by kevin on 2017/5/29.
 */
public class Demo {
    OrdersDao ordersDao = new OrdersDaoImpl();
    @Test
    public void findOrders_Test(){
        List<Orders> ordersList = ordersDao.findOrdersByUserId("3428936d-635f-408b-bf56-1986efd2493c");
        for(Orders o: ordersList){
            System.out.println(o.getId()+">>>"+o.getOrdernum()+">>>"+o.getPrice());
        }
    }

    @Test
    public void findOrderByState(){
        List<Orders> ordersList = ordersDao.findOrdersByState(0);
        System.out.println(ordersList.size());
        for(Orders o: ordersList){
            System.out.println(o.getPrice());
            System.out.println(o.getUser().getUserName());
        }

    }

    @Test
    public void showOrdersDetails(){
        Orders orders = ordersDao.findOrdersById("a41f3136-4be5-44b7-baac-711daf07b670");
        System.out.println(orders.getUser().getUserName());

        List<OrdersItem> items = orders.getItems();
        System.out.println(items.get(0).getBook().getName());
    }

}
