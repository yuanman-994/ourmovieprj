package com.movieprj.controllers;

import com.movieprj.services.ArticleService;
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

    @RequestMapping("/self_article_manage")
    public String self_article_manage() {
        return "self_article_manage";
    }

    @PostMapping("/add_article")
    @ResponseBody
    public int add_article(@RequestBody Map<String, String> data) {
        Subject currentSubject = SecurityUtils.getSubject();
        String author_name = (String) currentSubject.getPrincipal();
        if (articleServiceImp.save_article(author_name, data))
            return 0;
        else
            return -1;
    }

    @RequestMapping("/upload_image")
    public @ResponseBody
    Map<String, Object> upload_image(@RequestParam("file") MultipartFile file,
                                     HttpServletRequest request) throws Exception {
        Map<String, Object> ret = new HashMap<>();

        Subject currentSubject = SecurityUtils.getSubject();
        String author_name = (String) currentSubject.getPrincipal();

        String path = System.getProperty("user.dir");
        String realPath = path + "\\src\\main\\resources\\static\\images";
        String location = articleServiceImp.upload_image(file, realPath, author_name);

        ret.put("location", location);

        return ret;
    }

    @GetMapping("/article_manage/get_self_article_data")
    @ResponseBody
    public String get_self_article_data() {
        Subject currentSubject = SecurityUtils.getSubject();
        String author_name = (String) currentSubject.getPrincipal();
        return articleServiceImp.get_self_article_data(author_name);
    }

    @PostMapping("/article/update_row")
    @ResponseBody
    public int update_row(@RequestBody Map<String, String> data) {
        return articleServiceImp.update_row(data);
    }

    @GetMapping("/article/get_text_by_id")
    @ResponseBody
    public String get_text_by_id(@RequestParam int article_id){
        return articleServiceImp.get_text_by_id(article_id);
    }
}
