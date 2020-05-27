package com.movieprj.controllers;

import com.movieprj.beans.Movies;
import com.movieprj.services.ArticleService;
import com.movieprj.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private MoviesService moviesService;

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

    @GetMapping("/search/searchMovie")
    @ResponseBody
    public Map<String,Object> searchMovie(@RequestParam("searchText") String searchText){
        Map<String, Object> map = moviesService.searchMovies(searchText,"datenone","statusnone");
        return map;
    }
}
