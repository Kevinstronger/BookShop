package com.test.service;

import com.test.entity.*;
import com.test.utils.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public interface BusinessService {
    void addCategory(Category category);
    List<Category> findAllCategory();
    void addBook(Book book);

    //后台查询图书使用
    Page findPageRecords(String pageNum);

    //按分类查询显示图书
    Page findPageRecords(String pageNum, String categoryId);

    Category findCategoryById(String id);

    void deleteBook(String bookId);
    void modifyBook(Book book);
    Book findBookById(String bookId);


    //用户
    void regist(User user);
    User login(String userName, String password);

    //生成订单
    void addOrders(Orders orders, User user);

    List<Orders> findOrdersByUserId(String id);

    List<Orders> findOrdersByState(int state);

    Orders findOrdersById(String ordersId);

    void processOrders(String ordersId);
}
