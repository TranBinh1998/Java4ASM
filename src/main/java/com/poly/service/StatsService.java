package com.poly.service;

import com.poly.dto.VideoLikedInfo;

import java.util.List;

public interface StatsService {
    List<VideoLikedInfo> findVideoLikedInfo();

}
