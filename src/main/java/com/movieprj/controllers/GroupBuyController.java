package com.movieprj.controllers;

import com.movieprj.services.groupbuy.GroupBuyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/group_buy")
public class GroupBuyController {
    @Autowired
    private GroupBuyServiceImp groupBuyServiceImp;

    @GetMapping("/get_group_buy_data_now")
    @ResponseBody
//    响应加载团购数据请求，返回json式字符串，当期团购
    public String get_group_buy_data_now(){
        return groupBuyServiceImp.getGroupBuyDataNow();
    }
    @GetMapping("/get_group_buy_data_past")
    @ResponseBody
//    响应加载团购数据请求，返回json式字符串,往期团购
    public String get_group_buy_data_past(){
        return groupBuyServiceImp.getGroupBuyDataPast();
    }
}
