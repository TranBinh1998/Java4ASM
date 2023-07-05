package com.poly.impl;

import com.poly.dao.AbstractDao;
import com.poly.dao.VideoDao;
import com.poly.entity.Video;

import java.util.List;

public class VideoDaoImpl extends AbstractDao<Video> implements VideoDao {

    @Override
    public Video findByid(Integer id) {
        return super.findById(Video.class, id);
    }

    @Override
    public  Video findByHref(String href) {
        String sql = "SELECT o FROM Video o WHERE o.href = ?0";
        return super.findOne(Video.class, sql, href );
    }

    @Override
    public List<Video> finAll() {
        return super.findAll(Video.class,true);
    }

    @Override
    public List<Video> findAll(int pageNumber, int pageSize) {
        return super.findAll(Video.class, true, pageNumber, pageSize);
    }



//    public static void main(String[] args) {
//        VideoDaoImpl videoDao = new VideoDaoImpl();
//
//        Video video = videoDao.findByHref("0a5NRTEhP8A&list=RD0a5NRTEhP8A&start_radio=1");
//
//        System.out.println( "Title" +video.getTitle());
//
//
//    }
}
