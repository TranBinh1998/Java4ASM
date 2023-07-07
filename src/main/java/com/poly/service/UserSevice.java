package com.poly.service;

import com.poly.entity.User;
import com.poly.entity.Video;

import java.util.List;

public interface UserSevice {

    User findById(Integer id);

    User findByEmail(String email);

    User findByUserName (String userName);

    User login(String userName, String password);

    List<User> findAll();

    List<User> findAll(int pageNumber, int pageSize);

    User resetPassword(String email);
    User register(String userName, String password, String email); // Đăng kí

    User update(User entity);

    User delete(String userName);

    List<User> findUserLikedByVideoHref(String href);






}
