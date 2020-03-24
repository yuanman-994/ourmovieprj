package com.movieprj.beans;

import java.util.List;

public class Movies {
    private Integer movie_id;  //id
    private String movie_name; //电影名
    private MoviesTypes type= new MoviesTypes();//电影类型（一对多）
    private String introduction;//电影介绍
    private  String cover;  //封面照片
    private  String photo; //剧照
    private  String director;  //导演
    private String actors; //主演
    private String sellpoint; //卖点
    private String rank; //星级/评分
    private String time; //上映时间
    private boolean top; //置顶状态
    private String duration; //时长
    private String countryAndreligion; //国家和地区
    private boolean onshow;  //正在上映or即将上映
    private List<Comment> commentList; //评论(一对多）


    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public MoviesTypes getType() {
        return type;
    }

    public void setType(MoviesTypes type) {
        this.type = type;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getSellpoint() {
        return sellpoint;
    }

    public void setSellpoint(String sellpoint) {
        this.sellpoint = sellpoint;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCountryAndreligion() {
        return countryAndreligion;
    }

    public void setCountryAndreligion(String countryAndreligion) {
        this.countryAndreligion = countryAndreligion;
    }

    public boolean isOnshow() {
        return onshow;
    }

    public void setOnshow(boolean onshow) {
        this.onshow = onshow;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "movie_id=" + movie_id +
                ", movie_name='" + movie_name + '\'' +
                ", type=" + type +
                ", introduction='" + introduction + '\'' +
                ", cover='" + cover + '\'' +
                ", photo='" + photo + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", sellpoint='" + sellpoint + '\'' +
                ", rank='" + rank + '\'' +
                ", time='" + time + '\'' +
                ", top=" + top +
                ", duration='" + duration + '\'' +
                ", countryAndreligion='" + countryAndreligion + '\'' +
                ", onshow=" + onshow +
                ", commentList=" + commentList +
                '}';
    }
}
