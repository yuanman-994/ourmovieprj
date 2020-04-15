package com.movieprj.controllers;

import com.movieprj.beans.GroupBuyBeans;
import com.movieprj.services.groupbuy.GroupBuyServiceImp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class GroupBuyController {
    @Autowired
    private GroupBuyServiceImp groupBuyServiceImp;



    //前台部分
    @RequestMapping("/group_buy")
    public String group_buy() {
        return "group_buy";
    }

    @GetMapping("/group_buy/get_group_buy_data_now")
    @ResponseBody
//    响应加载团购数据请求，返回json式字符串，当期团购
    public String get_group_buy_data_now(){
        return groupBuyServiceImp.getGroupBuyDataNow();
    }
    @GetMapping("/group_buy/get_group_buy_data_past")
    @ResponseBody
//    响应加载团购数据请求，返回json式字符串,往期团购
    public String get_group_buy_data_past(){
        return groupBuyServiceImp.getGroupBuyDataPast();
    }

    //后台部分
    @RequestMapping("/group_buy_manage")
    public String group_buy_manage() {
        return "group_buy_manage";
    }

    @RequestMapping("/group_buy_add")
    public String group_buy_add() {
        return "group_buy_add";
    }

    @GetMapping("/group_buy/get_cinema")
    @ResponseBody
    public String get_cinema(){//影院信息
        return groupBuyServiceImp.getCinema();
    }

    @PostMapping("/group_buy/add_group_buy")
    @ResponseBody
    public int add_group_buy(@RequestBody GroupBuyBeans groupBuyBeans) {
        //此处应检查用户权限（是否可以添加该影院的团购信息）
        return groupBuyServiceImp.saveGroupBuy(groupBuyBeans);
    }
}
