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

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }


}