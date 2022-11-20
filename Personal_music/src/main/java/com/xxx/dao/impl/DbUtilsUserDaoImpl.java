package com.xxx.dao.impl;

import com.xxx.bean.User;
import com.xxx.dao.IUserDao;
import com.xxx.util.DbcpPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DbUtilsUserDaoImpl implements IUserDao {
    // 创建QueryRunner对象
    private QueryRunner queryRunner = new QueryRunner();
    @Override
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM tb_users WHERE username= '" +
                username + "' AND password = '" + password + "'";
        User user = null;
        try {
            user = queryRunner.query(DbcpPool.getConnection(), sql,
                    new BeanHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user == null ? false : true;
    }
    @Override
    public int save(User user) {
        String sql = "INSERT INTO tb_users (username,password)" +
                "VALUES (?,?)";
        Object params[] = { user.getUsername(), user.getPassword()};
        int result = 0;
        try {
            result = queryRunner.update(DbcpPool.getConnection(), sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int delete(String username) {
        String sql = "DELETE FROM tb_users WHERE username = ?";
        int result = 0;
        try {
            result = queryRunner.update(DbcpPool.getConnection(), sql, username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int update(String username, User user) {
        String sql = "UPDATE tb_users SET username = ?,password = ? WHERE username = ?";
        Object[] params = { user.getUsername(), user.getPassword(), username};
        int result = 0;
        try {
            result = queryRunner.update(DbcpPool.getConnection(), sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM tb_users ORDER BY username";
        List<User> list = null;
        try {
            list = queryRunner.query(DbcpPool.getConnection(), sql,
                    new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM tb_users WHERE username = '" + username + "'";
        User user = null;
        try {
            user = queryRunner.query(DbcpPool.getConnection(), sql,
                    new BeanHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
