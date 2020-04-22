package com.movieprj.beans;

import java.util.List;

public class Movies {
    private Integer movie_id;  //id
    private String movie_name; //电影名
    private List<MoviesTypes> moviesTypesList;//电影类型（一对多）
    private String intro;//电影介绍
    private  String cover;  //封面照片
    private  String photo; //剧照
    private  String director;  //导演
    private String main_actor; //主演
    private String sellpoint; //卖点
    private Integer rank; //星级/评分
    private String release_date; //上映时间
    private boolean top; //置顶状态
    private String duration; //时长
    private String countryAndreligion; //国家和地区
    private Integer onshow;  //正在上映or即将上映
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


    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public String getMain_actor() {
        return main_actor;
    }

    public void setMain_actor(String main_actor) {
        this.main_actor = main_actor;
    }

    public String getSellpoint() {
        return sellpoint;
    }

    public void setSellpoint(String sellpoint) {
        this.sellpoint = sellpoint;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
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

    public Integer getOnshow() {
        return onshow;
    }

    public void setOnshow(Integer onshow) {
        this.onshow = onshow;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }


    public List<MoviesTypes> getMoviesTypesList() {
        return moviesTypesList;
    }

    public void setMoviesTypesList(List<MoviesTypes> moviesTypesList) {
        this.moviesTypesList = moviesTypesList;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "movie_id=" + movie_id +
                ", movie_name='" + movie_name + '\'' +
                ", moviesTypesList=" + moviesTypesList +
                ", intro='" + intro + '\'' +
                ", cover='" + cover + '\'' +
                ", photo='" + photo + '\'' +
                ", director='" + director + '\'' +
                ", main_actor='" + main_actor + '\'' +
                ", sellpoint='" + sellpoint + '\'' +
                ", rank='" + rank + '\'' +
                ", release_date='" + release_date + '\'' +
                ", top=" + top +
                ", duration='" + duration + '\'' +
                ", countryAndreligion='" + countryAndreligion + '\'' +
                ", onshow=" + onshow +
                ", commentList=" + commentList +
                '}';
    }
}
