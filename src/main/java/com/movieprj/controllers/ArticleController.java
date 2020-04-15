package com.movieprj.controllers;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieprj.services.ArticleServiceImp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ArticleController {
    @Autowired
    private ArticleServiceImp articleServiceImp;

    @RequestMapping("/article_editor")
    public String article_editor() {
        return "article_editor";
    }

    @RequestMapping("/article_view")
    public String article_view() {
        return "article_view";
    }

    @RequestMapping("/article_check")
    public String article_check() {
        return "article_check";
    }

    @RequestMapping("/all_article_manage")
    public String all_article_manage() {
        return "all_article_manage";
    }

    @RequestMapping("/self_article_manage")
    public String self_article_manage() {
        return "self_article_manage";
    }

    @PostMapping("/add_article")
    @ResponseBody
    public int add_article(@RequestBody Map<String, String> data) {
        Subject currentSubject = SecurityUtils.getSubject();
        String author_name = (String) currentSubject.getPrincipal();
        if (articleServiceImp.saveArticle(author_name, data))
            return 0;
        else
            return -1;
    }

    @RequestMapping("/upload_image")
    public @ResponseBody
    Map<String, Object> upload_image(@RequestParam("file") MultipartFile file,@RequestParam("article_id") String article_id,
                                     HttpServletRequest request) throws Exception {
        Map<String, Object> ret = new HashMap<>();

        Subject currentSubject = SecurityUtils.getSubject();
        String author_name = (String) currentSubject.getPrincipal();

        String path = System.getProperty("user.dir");
        String realPath = path + "\\src\\main\\resources\\static\\images";
        String location = articleServiceImp.uploadImage(file, realPath, author_name,article_id);

        ret.put("location", location);

        return ret;
    }

    @GetMapping("/article_manage/get_self_article_data")
    @ResponseBody
    public String get_self_article_data() {
        Subject currentSubject = SecurityUtils.getSubject();
        String author_name = (String) currentSubject.getPrincipal();
        return articleServiceImp.getSelfArticleData(author_name);
    }

    @GetMapping("/article_manage/get_all_article_data")
    @ResponseBody
    public String get_all_article_data() {
        return articleServiceImp.getAllArticleData();
    }

    @PostMapping("/article/update_row")
    @ResponseBody
    public int update_row(@RequestBody Map<String, String> data) {
        return articleServiceImp.updateRow(data);
    }

    @GetMapping("/article/get_text_by_id")
    @ResponseBody
    public String get_text_by_id(@RequestParam int article_id){
        return articleServiceImp.getTextById(article_id);
    }

    @PostMapping("/article/upload_article")
    @ResponseBody
    public int upload_article(@RequestBody Map<String, String> data){
        return articleServiceImp.uploadArticle(data);
    }

    @PostMapping("/article/del_article")
    @ResponseBody
    public int del_article(String[] ids){
        return articleServiceImp.delArticle(ids);
    }

    @PostMapping("/article/update_check")
    @ResponseBody
    public int update_check(@RequestBody String datas){
        JSONArray jsArr = JSONObject.parseArray(datas);
        return articleServiceImp.updateCheck(jsArr);
    }
}
