package com.poly.service.impl;

import com.poly.dao.HistoryDao;
import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.impl.HistoryDaoImpl;
import com.poly.service.HistoryService;
import com.poly.service.VideoService;

import java.sql.Timestamp;
import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    // Khai báo dao
    private HistoryDao dao;

    private VideoService videoService = new VideoServiceImpl();

    public HistoryServiceImpl() {
        dao = new HistoryDaoImpl();

    }

    @Override
    public List<History> findByUser(String userName) {
        return dao.findByUser(userName);
    }

    @Override
    public List<History> findByUserAndIsLiked(String userName) {
        return dao.findByUserAndIsLiked(userName);
    }

    @Override
    public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
        return dao.findByUserIdAndVideoId(userId, videoId);
    }

    @Override
    public History create(User user, Video video) {
        History existHistory = findByUserIdAndVideoId(user.getId(), video.getId());
        if (existHistory == null) {
            existHistory = new History();
            existHistory.setUser(user);
            existHistory.setVideo(video);
            existHistory.setViewedDate(new Timestamp(System.currentTimeMillis()));
            existHistory.setLiked(Boolean.FALSE);
            return dao.create(existHistory);
        }
        return existHistory;


    }

    @Override
    public boolean updateLikeOrUnlike(User user, String videoHref) {
        Video video = videoService.findByHref(videoHref);
        History exitsHistory = findByUserIdAndVideoId(user.getId(), video.getId());
        if (exitsHistory.getLiked() == Boolean.FALSE) {
            exitsHistory.setLiked(Boolean.TRUE);
            exitsHistory.setLikedDate(new Timestamp(System.currentTimeMillis())); // Lấy giờ hệ thống
        }else {
            exitsHistory.setLiked(Boolean.FALSE);
            exitsHistory.setLikedDate(null);
        }
        History updateHistory = dao.update(exitsHistory);
        // Nếu updateHistory != null trả về true không thì trả về fasle
        return updateHistory != null ? true : false;
    }

    @Override
    public boolean updateLikeOrUnlikeForFindById(User id, Integer videoId) {
        Video video = videoService.findByid(videoId);
        History exitsHistory = findByUserIdAndVideoId(id.getId(), video.getId());
        if (exitsHistory.getLiked() == Boolean.FALSE) {
            exitsHistory.setLiked(Boolean.TRUE);
            exitsHistory.setLikedDate(new Timestamp(System.currentTimeMillis())); // Lấy giờ hệ thống
        }else {
            exitsHistory.setLiked(Boolean.FALSE);
            exitsHistory.setLikedDate(null);
        }
        History updateHistory = dao.update(exitsHistory);
        // Nếu updateHistory != null trả về true không thì trả về fasle
        return updateHistory != null ? true : false;
    }
}


