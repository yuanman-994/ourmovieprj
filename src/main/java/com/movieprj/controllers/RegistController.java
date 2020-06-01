package com.movieprj.controllers;

import com.movieprj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import  com.movieprj.beans.User;

import  javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class RegistController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/Regist")
    public String Regist(HttpServletRequest request) {
        String pageUrl = "user/register";
        return pageUrl;
    }

    @RequestMapping(value = "/register")
    public String register(User user){
        userService.regist(user);
        return "/faq";
    }
}
