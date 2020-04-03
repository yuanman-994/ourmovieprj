package com.movieprj.controllers;

import com.movieprj.beans.UserPassword;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "账号或密码错误！";
        }
        return "登录成功";
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
}