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
    public String hello(){
        return "article_editor";
    }

    @PostMapping("/upload_article")
    @ResponseBody
    public int upload_article(@RequestBody Map<String,String> data){
        Subject currentSubject = SecurityUtils.getSubject();
        String author_name = (String) currentSubject.getPrincipal();
        if (articleServiceImp.save_article(author_name,data))
            return 0;
        else
            return -1;
    }

    @RequestMapping("/upload_image")
    public @ResponseBody Map<String, Object> upload_image( @RequestParam("file") MultipartFile file,
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
}
