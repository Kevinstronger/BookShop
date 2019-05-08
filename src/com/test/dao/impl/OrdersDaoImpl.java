package com.test.dao.impl;

import com.test.dao.OrdersDao;
import com.test.entity.Book;
import com.test.entity.Orders;
import com.test.entity.OrdersItem;
import com.test.entity.User;
import com.test.exception.DaoException;
import com.test.utils.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kevin on 2017/5/25.
 */
public class OrdersDaoImpl implements OrdersDao {
    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    //保存订单信息到数据库中
    @Override
    public void addOrders(Orders orders, User user) {
        try {
            qr.update("insert into orders(id, ordernum, num, price, user_id) values(?,?,?,?,?)",
                    orders.getId(), orders.getOrdernum(), orders.getNum(), orders.getPrice(), user.getId());
            List<OrdersItem> items = orders.getItems();
            if(items!=null&&items.size()>0){
                String sql = "insert into ordersitem(id, num, price, orders_id, book_id) values(?,?,?,?,?)";
                Object pps[][] = new Object[items.size()][];
                for(int i=0; i<items.size(); i++){
                    OrdersItem item = items.get(i);
                    pps[i] = new Object[]{item.getId(), item.getNum(), item.getPrice(), orders.getId(), item.getBook().getId()};
                }
                qr.batch(sql, pps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Orders> findOrdersByUserId(String id) {
        try {
            return qr.query("SELECT * FROM orders WHERE user_id=? ORDER BY ordernum DESC", new BeanListHandler<Orders>(Orders.class), id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    //还要查询出订单所属用户的信息
    public List<Orders> findOrdersByState(int state) {
        try {
            List<Orders> ordersList = qr.query("SELECT * FROM orders WHERE state=? ORDER BY ordernum DESC", new BeanListHandler<Orders>(Orders.class), state);
            if(ordersList!= null|| ordersList.size()>0){
                for(Orders o: ordersList){
                    User user = qr.query("SELECT * FROM USER WHERE id=(SELECT user_id FROM orders WHERE id=?)", new BeanHandler<User>(User.class), o.getId());
                    o.setUser(user);
                }

            }
            return ordersList;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    //订单的明细还要查出来;明细中还要查询出书的信息；查询出用户出来
    @Override
    public Orders findOrdersById(String ordersId) {
        try {
            Orders orders = qr.query("select * from orders where id=?", new BeanHandler<Orders>(Orders.class), ordersId);
            if(orders!= null){
                User user = qr.query("SELECT * FROM USER WHERE id=(SELECT user_id FROM orders WHERE id=?)", new BeanHandler<User>(User.class), ordersId);
                orders.setUser(user);
                List<OrdersItem> itemList = qr.query("select * from ordersitem where orders_id=?", new BeanListHandler<OrdersItem>(OrdersItem.class), ordersId);
                if(itemList!=null||itemList.size()>0){
                    for(OrdersItem o: itemList){
                        Book book = qr.query("SELECT * FROM book WHERE id=(SELECT book_id FROM ordersitem WHERE id=?)", new BeanHandler<Book>(Book.class), o.getId());
                        o.setBook(book);
                    }
                }
                orders.setItems(itemList);
            }
            return orders;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public void processOrders(String ordersId) {
        try {
            qr.update("Update orders set state=1 where id=?", ordersId);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
