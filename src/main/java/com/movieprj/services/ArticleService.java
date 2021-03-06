package com.movieprj.services;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ArticleService {
    public Boolean saveArticle(String author_name, Map<String,String> data);
    public String uploadImage(MultipartFile image, String basePath, String author_name, String article_id);
    public String getSelfArticleData(String author_name);
    public String getAllArticleData();
    public int updateRow(Map<String,String> row);
    public String getTextById(int article_id);
    public int uploadArticle(Map<String,String> data);
    public int delArticle(String[] ids);
    public int updateCheck(JSONArray ja);
    public int uploadCover(MultipartFile image, String basePath, int article_id);
    public String getCover(int article_id);
    public int getTotalPage(int type, int total_per_page);//总页数
    public String getData(int type,int total_per_page,int aim);//获取某一页对应的数据
    public String getSingle(int article_id);
    public String getUpdatedNews();
    public void submitComment(int article_id,int user_id,String content);
    public void addClick(int article_id);//增加一次点击量

    public String getArticleWithLimit(int limit,int offset,String search);
//    public String getArticleWithLimitAndSearch();
    public String getHotArticle();
    public int setHot(int id,int article_id);

}
