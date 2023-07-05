package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "history")
public class History {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

     @ManyToOne(cascade = CascadeType.MERGE)
     @JoinColumn(name = "userId", referencedColumnName = "id")
     @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
     private User user;

     @ManyToOne(cascade = CascadeType.MERGE)
     @JoinColumn(name = "videoId", referencedColumnName = "id")
     @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Video video;

    @Column(name = "viewedDate")
    @CreationTimestamp  // Khi mình new 1 entity vào database thì sẽ lấy thời gian hiện tại
    private Timestamp viewedDate;

    @Column(name = "isLiked")
    private Boolean isLiked;

    @Column(name = "likedDate")
    private Timestamp likedDate;

    public History() {
    }

    public History(Integer id, User user, Video video, Timestamp viewedDate, Boolean isLiked, Timestamp likedDate) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.viewedDate = viewedDate;
        this.isLiked = isLiked;
        this.likedDate = likedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Timestamp getViewedDate() {
        return viewedDate;
    }

    public void setViewedDate(Timestamp viewedDate) {
        this.viewedDate = viewedDate;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public Timestamp getLikedDate() {
        return likedDate;
    }

    public void setLikedDate(Timestamp likedDate) {
        this.likedDate = likedDate;
    }
}
