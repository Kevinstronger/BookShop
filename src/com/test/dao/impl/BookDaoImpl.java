package com.test.dao.impl;

import com.test.dao.BookDao;
import com.test.entity.Book;
import com.test.exception.DaoException;
import com.test.utils.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */
public class BookDaoImpl implements BookDao {
    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    @Override
    public void addBook(Book book) {
        try {
            String sql = "insert into book(id, name, author, price, image, description, category_id) values(?,?,?,?,?,?,?)";
            qr.update(sql, book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getImage(), book.getDescription(), book.getCategory_id());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int getTotalRecords() {
        try {
             Long num = (long)qr.query("select count(*) from book", new ScalarHandler(1));
             return num.intValue();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int getTotalRecords(String categoryId) {
        try {
            Long num = (long)qr.query("select count(*) from book where category_id=?", new ScalarHandler(1), categoryId);
            return num.intValue();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> findPageBooks(int startIndex, int pageSize) {
        try {
            return qr.query("select * from book limit ?,?", new BeanListHandler<Book>(Book.class), startIndex, pageSize);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> findPageBooks(String categoryId, int startIndex, int pageSize) {
        try {
            return qr.query("select * from book where category_id=? limit ?,?", new BeanListHandler<Book>(Book.class), categoryId, startIndex, pageSize);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteBook(String bookId) {
        try {
            qr.update("DELETE FROM book WHERE id=?", bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyBook(Book book) {
        try {
            qr.update("update book set name=?, author=?, price=?, image=?, description=?, category_id=? where id=?",
                    book.getName(), book.getAuthor(),book.getPrice(),book.getImage(), book.getDescription(), book.getCategory_id(), book.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book findBookById(String bookId) {
        try {
            if (!"".equals(bookId) || bookId != null) {
                return qr.query("select * from book where id=?", new BeanHandler<Book>(Book.class), bookId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
