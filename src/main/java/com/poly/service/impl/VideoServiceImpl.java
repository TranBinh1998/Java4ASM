package com.poly.service.impl;

import com.poly.dao.VideoDao;
import com.poly.entity.Video;
import com.poly.impl.VideoDaoImpl;
import com.poly.service.VideoService;

import java.util.List;

public class VideoServiceImpl implements VideoService {

    private VideoDao dao;

    public VideoServiceImpl() {
        dao = new VideoDaoImpl();
    }

    @Override
    public Video findByid(Integer id) {
        return dao.findByid(id);
    }

    @Override
    public Video findByHref(String href) {

        return dao.findByHref(href);
    }

    @Override
    public List<Video> finAll() {
        return dao.finAll();
    }

    @Override
    public List<Video> findAll(int pageNumber, int pageSize) {
        return dao.findAll(pageNumber, pageSize);
    }

    @Override
    public Video create(Video entity) {
        entity.setActive(Boolean.TRUE);
        entity.setViews(0);
        entity.setShares(0);

        return dao.create(entity);
    }

    @Override
    public Video update(Video entity) {
        return dao.update(entity);
    }


    // Xóa theo kiểu xét active về 0
    @Override
    public Video delete(String href) {
        Video entity = findByHref(href);
        entity.setActive(Boolean.FALSE);
        return update(entity);
    }
}
