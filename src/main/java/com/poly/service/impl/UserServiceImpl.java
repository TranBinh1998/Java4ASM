package com.poly.service.impl;

import com.poly.dao.UserDao;
import com.poly.entity.User;
import com.poly.impl.UserDaoImpl;
import com.poly.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UserServiceImpl implements UserSevice {

    private UserDao dao;

    public  UserServiceImpl() {
        dao = new UserDaoImpl();
    }

    @Override
    public User findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public User findByUserName(String userName) {
        return dao.findByUserName(userName);
    }

    @Override
    public User login(String userName, String password) {

        return dao.findByUserNameAndPassword(userName, password);
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public List<User> findAll(int pageNumber, int pageSize) {
        return dao.findAll(pageNumber, pageSize);
    }

    @Override
    public User resetPassword(String email) {
        // Còn có phần gửi mail

        return null;
    }

    // Tạo một đối tượng PasswordEncoder
//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    private PasswordEncoder encoder;

    @Override
    public User register(String userName, String password, String email) {
        String userNameToDataBase = userName.toLowerCase();
        // Tạo entity
        User newUser = new User();
        newUser.setUserName(userNameToDataBase);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setAdmin(Boolean.FALSE);
        newUser.setActive(Boolean.TRUE);

        // Sau khi xử lí các thông tin mới gọi xuống dao
        return dao.create(newUser);
    }

    @Override
    public User update(User entity) {
        return dao.update(entity);
    }

    @Override
    public User delete(String userName) {
        User user = dao.findByUserName(userName);
        // Không xóa thực thể ra khỏi database chỉ tắt kích hoạt của tài khoản đó

        user.setActive(Boolean.FALSE);

        return dao.update(user);
    }
}
