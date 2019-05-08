package com.test.web.controller;

import com.test.entity.*;
import com.test.service.BusinessService;
import com.test.service.impl.BusinessServiceImpl;
import com.test.utils.Page;
import com.test.utils.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/13.
 */
@WebServlet(name = "ClientController")
public class ClientController extends HttpServlet {

    private BusinessService bs = new BusinessServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if("showIndexCategory".equals(operation)){
            showIndexCategory(request, response);
        }
        if("showBooksByCategory".equals(operation)){
            showBooksByCategory(request, response);
        }
        if("regist".equals(operation)){
            regist(request, response);
        }
        if("login".equals(operation)){
            login(request, response);
        }
        if("logout".equals(operation)){
            logout(request, response);
        }
        if("buyBook".equals(operation)){
            buyBook(request, response);
        }
        if("genOrders".equals(operation)){
            genOrders(request, response);
        }
        if("showUserOrders".equals(operation)){
            showUserOrders(request, response);
        }
        if("showOrdersDetails".equals(operation)){
            showOrdersDetails(request, response);
        }
    }

    private void showOrdersDetails(HttpServletRequest request, HttpServletResponse response) {
        try {
            String ordersId = request.getParameter("ordersId");
            Orders orders = bs.findOrdersById(ordersId);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/client/showordersdetails.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据用户的id查询自己的订单
    private void showUserOrders(HttpServletRequest request, HttpServletResponse response) {
        //判断用户是否登录
        try {
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            if(user == null) {
                request.setAttribute("message", "请先登录,2秒后自动转向登录页面。<meta http-equiv='Refresh' content='2;URL=" + request.getContextPath() + "/client/login.jsp'>");
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                return;
            }

            List<Orders> ordersList = bs.findOrdersByUserId(user.getId());
            request.setAttribute("os", ordersList);
            request.getRequestDispatcher("/client/orderslist.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    private void genOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null){
            request.setAttribute("message", "请先登录！2秒后自动转向登录页面。<meta http-equiv='Refresh' content='2;URL="+request.getContextPath()+"/client/login.jsp'>");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        //用户已登录，取出购物车
        Cart cart = (Cart) session.getAttribute("cart");
        //把cart中的数据填充的模型中
        Orders orders = new Orders();
        orders.setNum(cart.getAmount());
        orders.setPrice(cart.getTotalPrice());
        //购物项
        List<OrdersItem> ordersItems = new ArrayList<OrdersItem>();
        for(Map.Entry<String, CartItem> item: cart.getItems().entrySet()){
            CartItem cartItem = item.getValue();
            OrdersItem ordersItem = new OrdersItem();
            ordersItem.setNum(cartItem.getQuantity());
            ordersItem.setPrice(Float.parseFloat(cartItem.getPrice().toString()));
            ordersItem.setBook(cartItem.getBook());
            ordersItems.add(ordersItem);
        }
        orders.setItems(ordersItems);

        bs.addOrders(orders, user);


        request.setAttribute("message", "订单已成功生成");
        request.getRequestDispatcher("/message.jsp").forward(request, response);



    }

    private void buyBook(HttpServletRequest request, HttpServletResponse response) {
        try {
            Book book = bs.findBookById(request.getParameter("bookId"));
            HttpSession sesssion = request.getSession();
            Cart cart = (Cart) sesssion.getAttribute("cart");
            if(cart == null){
                cart = new Cart();
                sesssion.setAttribute("cart", cart);
            }
            cart.addBook(book);
            request.setAttribute("message", "购买成功");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if(session!=null){
                session.removeAttribute("user");
            }
            response.sendRedirect(request.getContextPath()+"/client/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            User user = bs.login(userName, password);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void regist(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = WebUtil.fillBean(request, User.class);
            bs.regist(user);
            request.setAttribute("message","<script type='text/javascript'fd>alert('用户注册成功!')</script>");
            response.sendRedirect(request.getContextPath()+"/client/login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void showBooksByCategory(HttpServletRequest request, HttpServletResponse response) {

        try {
            String categoryId = request.getParameter("categoryId");
            String pageNum = request.getParameter("pageNum");
            Page page = bs.findPageRecords(pageNum, categoryId);
            page.setUrl("/ClientController?operation=showBooksByCategory&categoryId="+categoryId);
            request.setAttribute("page", page);
            request.getRequestDispatcher("/client/welcome.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //查询所有分类， 封装后便于前端页面显示
    //查询所有的书籍，并分页显示
    private void showIndexCategory(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Category> categories = bs.findAllCategory();
            request.getSession().setAttribute("cs", categories);
            String pageNum = request.getParameter("pageNum");
            Page page = bs.findPageRecords(pageNum);
            page.setUrl("/ClientController?operation=showIndexCategory");
            request.setAttribute("page", page);
            request.getRequestDispatcher("/client/welcome.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
