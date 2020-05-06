package com.movieprj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String path = System.getProperty("user.dir");
        String image_dir_path = path + "\\src\\main\\resources\\static\\images\\articleImages\\";
        String image_cover_path = path +"\\src\\main\\resources\\static\\images\\articleCoverImages\\";
        registry.addResourceHandler("/images/articleImages/**").addResourceLocations("file:"+image_dir_path);
        registry.addResourceHandler("/images/articleCoverImages/**").addResourceLocations("file:"+image_cover_path);
    }
}