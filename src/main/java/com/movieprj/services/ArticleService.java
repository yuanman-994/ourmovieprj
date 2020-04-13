package com.movieprj.services;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ArticleService {
    public Boolean save_article(String author_name, Map<String,String> data);
    public String upload_image(MultipartFile image, String basePath, String author_name,String article_id);
    public String get_self_article_data(String author_name);
    public String get_all_article_data();
    public int update_row(Map<String,String> row);
    public String get_text_by_id(int article_id);
    public int upload_article(Map<String,String> data);
    public int del_article(String[] ids);
    public int update_check(JSONArray ja);
}
