package com.movieprj.controllers;

import com.alibaba.fastjson.JSONObject;
import com.movieprj.beans.UserPassword;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @GetMapping("/login")
    @ResponseBody
    public String login(UserPassword user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUser_name(),
                user.getPassword()
        );
        JSONObject jsonObject = new JSONObject();
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            if (subject.hasRole("admin")){
                subject.logout();
                jsonObject.put("status",-1);
                jsonObject.put("msg","管理员用户请从后台登录入口登录");
                return jsonObject.toString();
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            jsonObject.put("status",-1);
            jsonObject.put("msg","账号或密码错误");
            return jsonObject.toString();
        }
        jsonObject.put("status",0);
        jsonObject.put("msg","登录成功");
        return jsonObject.toString();
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout() {//登出
        return "logout";
    }

    @GetMapping("/get_user_inf")
    public String get_user_inf() {//返回用户信息，目前只返回用户名。未来应加入头像信息相关
        Subject currentSubject = SecurityUtils.getSubject();
        if (currentSubject.isAuthenticated()){
            return (String) currentSubject.getPrincipal();//返回用户名
        }
        else{
            return "#no-login";//未登录
        }
    }


    @GetMapping("/admin_login")
    @ResponseBody
    public String adminLogin(UserPassword user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUser_name(),
                user.getPassword()
        );
        JSONObject jsonObject = new JSONObject();
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            if (!subject.hasRole("admin")){
                subject.logout();
                jsonObject.put("status",-1);
                jsonObject.put("msg","普通用户请从前台登录");
                return jsonObject.toString();
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            jsonObject.put("status",-1);
            jsonObject.put("msg","账号或密码错误");
            return jsonObject.toString();
        }
        jsonObject.put("status",0);
        jsonObject.put("msg","登录成功");
        return jsonObject.toString();
    }
}