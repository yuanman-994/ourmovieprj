package com.movieprj.controllers;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

    @RequestMapping("/news_single")
    public String news_single() {
        return "news_single";
    }

    @RequestMapping("/see_movie")
    public String see_movie() {
        return "see_movie";
    }

    /*@RequestMapping("/movie_single")
    public String movie_single() {
        return "movie_single";
    }*/

    @RequestMapping("/group_buy")
    public String group_buy() {
        return "group_buy";
    }

    @RequestMapping("/about_us")
    public String about_us(){ return "about_us";}

    @RequestMapping("/cooperation")
    public String cooperation(){ return "cooperation";}

    @RequestMapping("/err")
    @ResponseBody
    public String err(){ return "没有权限!";}

    @RequestMapping("/please_login_first")
    @ResponseBody
    public String please_login_first(){ return "请先登录!";}

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World !!!";
    }
}