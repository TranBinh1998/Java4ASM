package com.poly.dao;

import com.poly.dto.VideoLikedInfo;
import com.poly.entity.User;

import java.util.List;

public interface StatsDao {
    List<VideoLikedInfo> findVideoLikedInfo();
}
