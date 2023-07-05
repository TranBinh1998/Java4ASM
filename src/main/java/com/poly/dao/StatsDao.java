package com.poly.dao;

import com.poly.dto.VideoLikeInfo;

import java.util.List;

public interface StatsDao {
    List<VideoLikeInfo> findVideoLikedInfo();


}
