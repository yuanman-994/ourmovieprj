package com.movieprj.beans;

public class Comment {
    private String comment_time;   //评论时间 yy/mm/dd/ HH:MM:SS
    private String content; //评论内容
    private Integer movie_id;  //评论电影id
    private Integer user_id;//发表用户id
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "comment_time='" + comment_time + '\'' +
                ", content='" + content + '\'' +
                ", movie_id=" + movie_id +
                ", user_id=" + user_id +
                ", user=" + user +
                '}';
    }
}
