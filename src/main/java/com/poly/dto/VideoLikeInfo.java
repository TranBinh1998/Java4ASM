package com.poly.dto;

public class VideoLikeInfo {
    private  Integer videoId;
    private String title;
    private String href;
    private Integer totalLike;

    public VideoLikeInfo(Integer videoId, String title, String href, Integer totalLike) {
        this.videoId = videoId;
        this.title = title;
        this.href = href;
        this.totalLike = totalLike;
    }


}
