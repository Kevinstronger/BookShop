package com.test.web.controller;

import com.test.entity.Book;
import com.test.entity.Category;
import com.test.entity.Orders;
import com.test.service.BusinessService;
import com.test.service.impl.BusinessServiceImpl;
import com.test.utils.Page;
import com.test.utils.WebUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/4.
 */
@WebServlet(name = "ManagerController")
public class ManagerController extends HttpServlet {
    private BusinessService bs = new BusinessServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String requestURI = request.getRequestURI();
        String operation = request.getParameter("operation");
        if("addCategory".equals(operation)){
            addCategory(request, response);
        }
        if("showAllCategory".equalsIgnoreCase(operation)){
            showAllCategory(request, response);
        }

        if("showCategoryUI".equalsIgnoreCase(operation)){
            showCategoryUI(request, response);
        }
        if("addBook".equalsIgnoreCase(operation)){
            addBook(request, response);
        }
        if("showAllBooks".equals(operation)){
            showAllBooks(request, response);
        }
        if("deleteBook".equals(operation)){
            deleteBook(request, response);
        }
        if("modifyBook".equals(operation)){
            modifyBook(request, response);
        }
        if("editUI".equals(operation)){
            editUI(request, response);
        }
        if("showUnprocessedOrders".equals(operation)){
            showUnprocessedOrders(request, response);
        }
        if("showOrdersDetails".equals(operation)){
            showOrdersDetails(request, response);
        }

        if("comfirmPO".equals(operation)){
            comfirmPO(request, response);
        }
        if("showProcessedOrders".equals(operation)){
            showProcessedOrders(request, response);
        }


    }

    private void showProcessedOrders(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Orders> ordersList = bs.findOrdersByState(1);
            request.setAttribute("os", ordersList);
            request.getRequestDispatcher("/manager/orderlist.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void comfirmPO(HttpServletRequest request, HttpServletResponse response) {
        String ordersId = request.getParameter("ordersId");
        bs.processOrders(ordersId);
        request.setAttribute("message", "订单已处理!");
        request.getRequestDispatcher("/message.jsp");
    }

    private void showOrdersDetails(HttpServletRequest request, HttpServletResponse response) {
        try {
            String ordersId = request.getParameter("ordersId");
            Orders orders = bs.findOrdersById(ordersId);//不但要完成前台的操作，还要查询出是哪个用户的订单
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/manager/showordersdetails.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //显示所有未发货的订单
    private void showUnprocessedOrders(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Orders> ordersList = bs.findOrdersByState(0);
            request.setAttribute("os", ordersList);
            request.getRequestDispatcher("/manager/orderlist.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editUI(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Category> categories = bs.findAllCategory();
            request.setAttribute("cs", categories);
            Book book = bs.findBookById(request.getParameter("bookId"));
            request.setAttribute("book", book);
            request.getRequestDispatcher("/manager/modifyBook.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //编辑图书
    private void modifyBook(HttpServletRequest request, HttpServletResponse response) {

        String storePath = getServletContext().getRealPath("/files");
        System.out.println("storePath = "+storePath);
        try {
            Book book = bs.findBookById(request.getParameter("bookId"));
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解析请求
            List<FileItem> items = upload.parseRequest(request);
            for(FileItem item: items){
                if(item.isFormField()){
                    //封装数据到javabean中
                    String fieldName = item.getFieldName();//字段名， 即javabean的属性名
                    String fieldValue = item.getString(request.getCharacterEncoding());
                    BeanUtils.setProperty(book, fieldName, fieldValue); //除了图片路径， 其他数据都有了
                }else{
                    //处理文件上传
                    InputStream in = item.getInputStream();
                    String fileName = item.getName();
                    fileName = UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("\\")+1);
                    //设置存储的图片名称
                    book.setImage(fileName);
                    OutputStream out = new FileOutputStream(storePath+"\\"+fileName);
                    byte b[] = new byte[1024];
                    int len = -1;
                    while((len=in.read(b))!=-1){
                        out.write(b, 0, len);
                    }
                    out.close();
                    in.close();
                    item.delete();//删除临时文件
                }

            }
            bs.modifyBook(book);
            request.setAttribute("message","<script type='text/javascript'fd>alert('编辑成功')</script>");
            response.sendRedirect("http://localhost:8080/bookshop/manager/header.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "服务器忙!");
            request.getRequestDispatcher("/message.jsp");
        }

    }

    //删除图书
    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        try {
            String bookId = request.getParameter("bookId");
            bs.deleteBook(bookId);
            request.setAttribute("message", "<script type='text/javascript'>alert('删除成功')</script>");
            response.sendRedirect("/manager/listBooks.jsp");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //显示图书列表
    private void showAllBooks(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pageNum = request.getParameter("pageNum");
            Page page = bs.findPageRecords(pageNum);
            page.setUrl("/ManagerController?operation=showAllBooks");
            request.setAttribute("page", page);
            request.getRequestDispatcher("/manager/listBooks.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加书籍到数据库中
    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String resultPath = "";
        String storePath = getServletContext().getRealPath("/files");
        System.out.println("storePath = "+storePath);
        try {
            Book book = new Book();
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解析请求
            List<FileItem> items = upload.parseRequest(request);
            for(FileItem item: items){
                if(item.isFormField()){
                    //封装数据到javabean中
                    String fieldName = item.getFieldName();//字段名， 即javabean的属性名
                    String fieldValue = item.getString(request.getCharacterEncoding());
                    BeanUtils.setProperty(book, fieldName, fieldValue); //除了图片路径， 其他数据都有了
                }else{
                    //处理文件上传
                    InputStream in = item.getInputStream();
                    String fileName = item.getName();
                    fileName = UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("\\")+1);
                    //设置存储的图片名称
                    book.setImage(fileName);
                    OutputStream out = new FileOutputStream(storePath+"\\"+fileName);
                    byte b[] = new byte[1024];
                    int len = -1;
                    while((len=in.read(b))!=-1){
                        out.write(b, 0, len);
                    }
                    out.close();
                    in.close();
                    item.delete();//删除临时文件
                }

            }
            //System.out.println(book);
            bs.addBook(book);
            resultPath = "/manager/addBook.jsp";
            request.setAttribute("message","<script type='text/javascript'fd>alert('添加成功')</script>");
        } catch (FileUploadException e) {
            e.printStackTrace();
            resultPath = "/message.jsp";
            request.setAttribute("message", "服务器忙!");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher(resultPath).forward(request, response);

    }

    //在添加图书页面中显示分类信息
    private void showCategoryUI(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Category> categories = bs.findAllCategory();
            request.setAttribute("cs", categories);
            request.getRequestDispatcher("/manager/addBook.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //显示分类列表
    private void showAllCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Category> categories = bs.findAllCategory();
            request.setAttribute("cs", categories);
            request.getRequestDispatcher("/manager/listCategory.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    //添加分类到库中
    private void addCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            Category category = WebUtil.fillBean(request, Category.class);
            bs.addCategory(category);
            request.setAttribute("message", "<script type='text/javascript'>alert('添加成功')</script>");
            request.getRequestDispatcher("/manager/addCategory.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
