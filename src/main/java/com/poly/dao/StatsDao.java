package com.poly.dao;

import com.poly.dto.VideoLikedInfo;

import java.util.List;

public interface StatsDao {
    List<VideoLikedInfo> findVideoLikedInfo();


}
