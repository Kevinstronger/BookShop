package com.test.dao.impl;

import com.test.dao.UserDao;
import com.test.entity.User;
import com.test.exception.DaoException;
import com.test.utils.DBCPUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by Administrator on 2017/5/14.
 */
public class UserDaoImpl implements UserDao {
    private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
    @Override
    public void addUser(User user) {
        try {
            qr.update("insert into user(id, userName, password, cellPhone, address, email) values (?,?,?,?,?,?)",
                    user.getId(), user.getUserName(), user.getPassword(), user.getCellPhone(), user.getAddress(), user.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserById(String userId) {
        if(userId!=null||!"".equals(userId)){
            try {
                return qr.query("SELECT * FROM USER WHERE id=?", new BeanHandler<User>(User.class), userId);
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return null;
    }

    @Override
    public User findUserByName(String userName, String password) {
        try {
            return qr.query("select * from user where userName=? and password=?", new BeanHandler<User>(User.class), userName, password);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
