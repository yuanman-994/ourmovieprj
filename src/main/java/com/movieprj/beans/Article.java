package com.movieprj.beans;

public class Article {
    private int article_id;//文章id
    private int author_id;//作者id
    private String headline;//标题
    private int click_num;//点击量
    private String release_time;//发布时间
    private String article_url;//文章资源地址
    private int type;//文章类型
    private int check_status;//文章审核状态，0为未审核，1为通过，-1为未通过

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public int getClick_num() {
        return click_num;
    }

    public void setClick_num(int click_num) {
        this.click_num = click_num;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCheck_status() {
        return check_status;
    }

    public void setCheck_status(int check_status) {
        this.check_status = check_status;
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", author_id=" + author_id +
                ", headline='" + headline + '\'' +
                ", click_num=" + click_num +
                ", release_time='" + release_time + '\'' +
                ", article_url='" + article_url + '\'' +
                ", type=" + type +
                ", check=" + check_status +
                '}';
    }
}
