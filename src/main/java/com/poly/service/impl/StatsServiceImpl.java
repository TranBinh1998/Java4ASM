package com.poly.service.impl;

import com.poly.dao.StatsDao;
import com.poly.dto.VideoLikedInfo;
import com.poly.impl.StatsDaoImpl;
import com.poly.service.StatsService;

import java.util.List;

public class StatsServiceImpl implements StatsService {

    private StatsDao statsDao;

    public StatsServiceImpl() {
        statsDao = new StatsDaoImpl();
    }

    @Override
    public List<VideoLikedInfo> findVideoLikedInfo() {
        return statsDao.findVideoLikedInfo();
    }
}
