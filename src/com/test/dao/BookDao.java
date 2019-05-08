package com.test.dao;

import com.test.entity.Book;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public interface BookDao {
    void addBook(Book book);
    //查询所有记录条数
    int getTotalRecords();
    //显示某类别的图书总数
    int getTotalRecords(String categoryId);
    List<Book> findPageBooks(int startIndex, int pageSize);
    List<Book> findPageBooks(String categoryId, int startIndex, int pageSize);
    //删除图书记录
    void deleteBook(String bookId);
    void modifyBook(Book book);

    Book findBookById(String bookId);
}
