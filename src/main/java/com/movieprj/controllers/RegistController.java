package com.movieprj.controllers;

import com.movieprj.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import  com.movieprj.beans.User;

import  javax.servlet.http.HttpServletRequest;

@Controller
public class RegistController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/Regist")
    public String Regist(HttpServletRequest request) {
        String pageUrl = "user/register";
        return pageUrl;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public String register(User user){
        return userService.regist(user);
    }
}
