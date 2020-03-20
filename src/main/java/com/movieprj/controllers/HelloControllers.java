package com.movieprj.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 模板引入测试
 */
@Controller
public class HelloControllers {

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/see_movie")
    public String see_movie() {
        return "see_movie";
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