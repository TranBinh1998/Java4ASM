package com.poly.dao;

import com.poly.entity.Video;

import java.util.List;

public interface VideoDao {
    Video findByid(Integer id);

    Video findByHref(String href);

    List<Video> finAll();

    List<Video> findAll(int pageNumber, int pageSize);

    Video create(Video entity);

    Video update(Video entity);

    Video delete(Video entity);




}
