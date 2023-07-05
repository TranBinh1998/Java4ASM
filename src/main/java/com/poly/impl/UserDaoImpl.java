package com.poly.impl;

import com.poly.dao.AbstractDao;
import com.poly.dao.UserDao;
import com.poly.entity.User;

import java.util.List;

// Thiết kế 1 lần sử dụng lại bằng kế thừa
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    @Override
    public User findById(Integer id) {

        return super.findById(User.class, id);
    }


    // FindOne
    @Override
    public User findByEmail(String email) {
        // Thiết kế sql
        String sql = "SELECT o FROM User o WHERE o.email = ?0";

        return super.findOne(User.class, sql, email);
    }

    @Override
    public User findByUserName(String userName) {

        String sql = "SELECT o FROM User o WHERE o.userName = ?0";

        return super.findOne(User.class, sql, userName);
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        String sql = "SELECT o FROM User o WHERE o.userName =?0 AND o.password = ?1";

        return super.findOne(User.class, sql, userName, password);
    }

    @Override
    public List<User> findAll() {
        // Chỉ hiển thị các tài khoản còn hoạt động (Active)
        return super.findAll(User.class, true);
    }

    @Override
    public List<User> findAll(int pageNumber, int pageSize) {
        return super.findAll(User.class, true, pageNumber, pageSize);
    }

    // Thừa
    @Override
    public User create(User entity) {
        return super.create(entity);
    }

    @Override
    public User update(User entity) {
        return super.update(entity);
    }

    @Override
    public User delete(User entity) {
        return super.delete(entity);
    }
}
