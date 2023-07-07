package com.poly.dao;

import com.poly.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
   User findById(Integer id);

   User findByEmail(String email);

    User findByUserName (String userName);

   User findByUserNameAndPassword(String userName, String password);

   List<User> findAll();

   List<User> findAll(int pageNumber, int pageSize);

   User create(User entity);

   User update(User entity);

   User delete(User entity);

   List<User> findUsersLikedVideoByVideoHref(Map<String, Object> params);

}
