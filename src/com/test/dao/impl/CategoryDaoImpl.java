package com.test.dao.impl;

import com.test.dao.CategoryDao;
import com.test.entity.Category;
import com.test.exception.DaoException;
import com.test.utils.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */
public class CategoryDaoImpl implements CategoryDao {
    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    @Override
    public void addCategory(Category category) {

        try {
            String sql="INSERT INTO category VALUES(?,?,?)";
            qr.update(sql, category.getId(), category.getName(), category.getDescription());
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public List<Category> findAllCategory() {
        try {
            return qr.query("select * from category", new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Category findById(String id) {
        try {
            return qr.query("SELECT * FROM category WHERE id=?", new BeanHandler<Category>(Category.class), id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
