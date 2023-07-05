package com.poly.impl;

import com.poly.dao.AbstractDao;
import com.poly.dao.HistoryDao;
import com.poly.entity.History;

import java.util.List;

public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao {

    @Override
    public List<History> findByUser(String userName) {
        String sql = "SELECT o FROM History o WHERE o.user.userName = ?0 AND o.video.isActive =1"
                + " ORDER BY o.viewedDate DESC";
        return super.finMany(History.class, sql, userName);
    }

    // Lấy các video người dùng đã like, Đã like = isLiked = 1
    @Override
    public List<History> findByUserAndIsLiked(String userName) {
        String sql = "SELECT o FROM History o WHERE o.user.userName = ?0 AND o.isLiked = 1" +
                " AND o.video.isActive = 1" +
                " ORDER BY o.viewedDate DESC";

        return super.finMany(History.class, sql, userName);
    }

    @Override
    public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
        String sql = "SELECT o FROM History o WHERE o.user.id = ?0 AND o.video.id =?1" +
                " AND o.video.isActive = 1";
        return super.findOne(History.class, sql, userId, videoId);
    }
}

