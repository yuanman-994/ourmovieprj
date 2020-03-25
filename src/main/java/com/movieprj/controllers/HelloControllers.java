package com.movieprj.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 模板引入测试
 */
@Controller
public class HelloControllers {


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/contact")
    public String contact() { return "contact"; }

    @RequestMapping("/dynamic")
    public String dynamic() {
        return "dynamic";
    }

    @RequestMapping("/see_movie")
    public String see_movie() {
        return "see_movie";
    }

    @RequestMapping("/movie_single")
    public String movie_single() {
        return "movie_single";
    }

    @RequestMapping("/group_buy")
    public String group_buy() {
        return "group_buy";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World !!!";
    }
}