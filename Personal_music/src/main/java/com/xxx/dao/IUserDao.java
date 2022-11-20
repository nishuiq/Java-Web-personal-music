package com.xxx.dao;

import java.util.List;
import com.xxx.bean.User;
public interface IUserDao {
    // 用户登录
    public boolean login(String username, String password);

    // 保存用户
    public int save(User user);

    // 根据用户名删除
    public int delete(String username);

    // 根据用户名修改用户信息
    public int update(String username, User user);

    // 查找所有用户信息
    public List<User> findAll();

    // 根据用户名查找单个用户
    public User findByUsername(String username);
}
