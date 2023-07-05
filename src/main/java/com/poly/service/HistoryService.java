package com.poly.service;

import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Video;

import java.util.List;

public interface HistoryService {

    List<History> findByUser(String userName);

    List<History> findByUserAndIsLiked(String userName);

    History findByUserIdAndVideoId(Integer userId, Integer videoId);

    History create(User user, Video video);

    boolean updateLikeOrUnlike(User id, String videoHref);

    boolean updateLikeOrUnlikeForFindById(User id, Integer videoId);

}
