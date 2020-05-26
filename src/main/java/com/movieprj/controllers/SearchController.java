package com.movieprj.controllers;

import com.movieprj.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping ("/search")
    public String  search(){
       return "search";
    }

    @GetMapping("/search/searchArticle")
    @ResponseBody
    public String searchArticle(@RequestParam("pageSize") int pageSize,
                                @RequestParam("aimPage") int aimPage,
                                @RequestParam("searchText") String searchText ){
//        System.out.println(pageSize+aimPage+searchText);
        int offset = pageSize*(aimPage-1);
        int limit = offset+pageSize;
        return articleService.getArticleWithLimit(limit,offset,searchText);
    }
}
