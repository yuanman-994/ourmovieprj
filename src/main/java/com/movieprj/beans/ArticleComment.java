package com.movieprj.beans;

public class ArticleComment {//文章评论
    private int article_id;
    private int user_id;
    private String content;
    private String time;
    private int comment_id;

    @Override
    public String toString() {
        return "ArticleComment{" +
                "article_id=" + article_id +
                ", user_id=" + user_id +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", comment_id=" + comment_id +
                '}';
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
