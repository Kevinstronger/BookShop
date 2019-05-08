package com.test.service.impl;

import com.test.dao.BookDao;
import com.test.dao.CategoryDao;
import com.test.dao.OrdersDao;
import com.test.dao.UserDao;
import com.test.dao.impl.BookDaoImpl;
import com.test.dao.impl.CategoryDaoImpl;
import com.test.dao.impl.OrdersDaoImpl;
import com.test.dao.impl.UserDaoImpl;
import com.test.entity.*;
import com.test.service.BusinessService;
import com.test.utils.IDGenerator;
import com.test.utils.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public class BusinessServiceImpl implements BusinessService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private OrdersDao ordersDao = new OrdersDaoImpl();
    @Override
    public void addCategory(Category category) {
        category.setId(IDGenerator.getPrimaryKey());
        categoryDao.addCategory(category);

    }

    @Override
    public List<Category> findAllCategory() {
        return categoryDao.findAllCategory();
    }

    @Override
    public void addBook(Book book) {
        book.setId(IDGenerator.getPrimaryKey());
        bookDao.addBook(book);
    }

    @Override
    public Page findPageRecords(String pageNum) {
        int num =1;
        if(pageNum != null&&!"".equals(pageNum)){
            num = Integer.parseInt(pageNum);
        }
        int totalRecords = bookDao.getTotalRecords();
        Page page = new Page(num, totalRecords);
        List<Book> bookList = bookDao.findPageBooks(page.getStartIndex(), page.getPageSize());
        page.setRecords(bookList);
        return page;
    }

    @Override
    public Page findPageRecords(String pageNum, String categoryId) {
        int num =1;
        if(pageNum != null&&!"".equals(pageNum)){
            num = Integer.parseInt(pageNum);
        }
        int totalRecords = bookDao.getTotalRecords(categoryId);
        Page page = new Page(num, totalRecords);
        List<Book> bookList = bookDao.findPageBooks(categoryId, page.getStartIndex(), page.getPageSize());
        page.setRecords(bookList);
        return page;
    }

    @Override
    public Category findCategoryById(String id) {
        return categoryDao.findById(id);
    }

    @Override
    public void deleteBook(String bookId) {
        bookDao.deleteBook(bookId);
    }

    @Override
    public void modifyBook(Book book) {
        bookDao.modifyBook(book);
    }

    @Override
    public Book findBookById(String bookId) {
        return bookDao.findBookById(bookId);
    }
    //用户注册
    @Override
    public void regist(User user) {
        user.setId(IDGenerator.getPrimaryKey());
        userDao.addUser(user);
    }
    //用户登录时验证
    @Override
    public User login(String userName, String password) {
        return userDao.findUserByName(userName, password);
    }

    @Override
    public void addOrders(Orders orders, User user) {
        orders.setId(IDGenerator.getPrimaryKey());
        orders.setOrdernum(IDGenerator.getOrderNum());
        //给购物项添加ID
        List<OrdersItem> items = orders.getItems();
        for(OrdersItem item: items){
            item.setId(IDGenerator.getPrimaryKey());
        }
        ordersDao.addOrders(orders, user);


    }

    @Override
    public List<Orders> findOrdersByUserId(String id) {
        return ordersDao.findOrdersByUserId(id);
    }

    @Override
    public List<Orders> findOrdersByState(int state) {
        return ordersDao.findOrdersByState(state);
    }

    @Override
    public Orders findOrdersById(String ordersId) {
        return ordersDao.findOrdersById(ordersId);
    }

    @Override
    public void processOrders(String ordersId) {
        ordersDao.processOrders(ordersId);
    }
}
