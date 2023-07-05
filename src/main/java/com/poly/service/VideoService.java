package com.poly.service;

import com.poly.entity.Video;

import java.util.List;

public interface VideoService {
    Video findByid(Integer id);

    Video findByHref(String href);

    List<Video> finAll();

    List<Video> findAll(int pageNumber, int pageSize);

    Video create(Video entity);

    Video update(Video entity);

    Video delete(String href);
}
