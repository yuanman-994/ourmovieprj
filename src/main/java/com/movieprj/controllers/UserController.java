package com.movieprj.controllers;

import com.movieprj.beans.User;
import com.movieprj.services.UserService;
import com.movieprj.services.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class UserController {


    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/user/change_name")
    @ResponseBody
    public int changeName(@RequestParam("new_name") String newName){//修改用户名
        Subject currentSubject = SecurityUtils.getSubject();
        if (!currentSubject.isAuthenticated())//检测是否登录
            return -1;
        String name = (String) currentSubject.getPrincipal();
        int res = userService.changeName(name,newName);//0 成功 -1 与其它用户名重复 -2 新旧名字相同
        if (res == 0){//修改用户名成功时自动登出
            currentSubject.logout();
        }
        return res;
    }

    @PostMapping("/user/change_password")
    @ResponseBody
    public int changePassword(@RequestBody Map<String,String> data){
        String password = data.get("password");
        String newPassword = data.get("newPassword");
        Subject currentSubject = SecurityUtils.getSubject();
        if (!currentSubject.isAuthenticated())//检测是否登录
            return -1;
        String name = (String) currentSubject.getPrincipal();

        int res = userService.changePassword(name,password,newPassword);
        if (res==0)
            currentSubject.logout();
        return res;
    }

    @GetMapping("/user/get_user_inf_detail")
    @ResponseBody
    public String getUserInf(){
        Subject currentSubject = SecurityUtils.getSubject();
        if (!currentSubject.isAuthenticated())//检测是否登录
            return "未登录";
        String name = (String) currentSubject.getPrincipal();
        return userService.getUserInf(name);
    }

    @PostMapping("/user/change_inf")
    @ResponseBody
    public int changeInf(User user){
        Subject currentSubject = SecurityUtils.getSubject();
        if (!currentSubject.isAuthenticated())//检测是否登录
            return -1;
        String name = (String) currentSubject.getPrincipal();

        return userService.setUserInf(name,user);
    }

}
