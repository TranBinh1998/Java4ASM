package com.poly.dao;

import com.poly.entity.History;

import java.util.List;

public interface HistoryDao {

    // Lọc lịch sử theo người dùng đã xem bằng userName
    List<History> findByUser(String userName);

    List<History> findByUserAndIsLiked(String userName);

    History findByUserIdAndVideoId(Integer userId, Integer videoId);

    History create(History entity);

    History update(History entity);







}
