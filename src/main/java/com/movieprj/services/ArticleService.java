package com.movieprj.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ArticleService {
    public Boolean save_article(String author_name, Map<String,String> data);
    public String upload_image(MultipartFile image, String basePath, String author_name);
}
