package com.movieprj.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieDetailPageController {
    @RequestMapping("/movie_detail_page")
    public String movie_detail(){
        return "movie_detail_page";
    }
}
