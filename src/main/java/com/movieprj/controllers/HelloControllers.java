package com.movieprj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 模板引入测试
 */
@Controller
public class HelloControllers {

    @RequestMapping("/back_index")
    public String back_index(){
        return "back_index";
    }

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




    @RequestMapping("/about_us")
    public String about_us(){ return "about_us";}

    @RequestMapping("/cooperation")
    public String cooperation(){ return "cooperation";}

    @RequestMapping("/lost_password")
    public String customer(){ return "lost_password";}

    @RequestMapping("/admin_index")
    public String admin_index(){ return "admin_index";}

    @RequestMapping("/faq")
    public String faq(){ return "faq";}

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


    @RequestMapping("/admin_login_page")
    public String adminLogin(){
        return "admin_login_page";
    }
}