package com.poly.impl;

import com.poly.dao.AbstractDao;
import com.poly.dao.StatsDao;
import com.poly.dto.VideoLikedInfo;

import java.util.ArrayList;
import java.util.List;

public class StatsDaoImpl extends AbstractDao<Object[]> implements StatsDao {
    private List<VideoLikedInfo> result;

    @Override
    public List<VideoLikedInfo> findVideoLikedInfo() {
        String sql = "select v.id, v.title, v.href, sum(cast(h.isLiked as int)) as totalLike" +
                " from video v left join history h on v.id = h.videoId" +
                " where v.isActive = 1" +
                " group by v.id, v.title, v.href" +
                " order by sum(cast(h.isLiked as int)) desc";

        List<Object[]> objects = super.findManyByNativeQuery(Object[].class, sql);
        List<VideoLikedInfo> result = new ArrayList<>();
        // Duyệt mảng objects lấy dữ liệu từ database này. Duyệt qua
       objects.forEach(object -> {
           // Tạo mới 1 đối tượng
           VideoLikedInfo videoLikedInfo = setDataVideoLikedInfo(object);
           result.add(videoLikedInfo);
       });


        return null;
    }

    private VideoLikedInfo setDataVideoLikedInfo(Object[] object) {
        // Set giá trị cho từng thuộc tính với giá trị lấy được database
        VideoLikedInfo videoLikedInfo =  new VideoLikedInfo();
        videoLikedInfo.setVideoId((Integer)object[0]);
        videoLikedInfo.setTitle((String) object[1]);
        videoLikedInfo.setHref((String) object[2]);
        videoLikedInfo.setTotalLike((Integer) object[3]);

        return videoLikedInfo;
    }

}
