package com.movieprj.controllers;

import com.movieprj.beans.GroupBuyBeans;
import com.movieprj.services.groupbuy.GroupBuyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String getGroupBuyDataNow(){
        return groupBuyServiceImp.getGroupBuyDataNow();
    }
    @GetMapping("/group_buy/get_group_buy_data_past")
    @ResponseBody
//    响应加载团购数据请求，返回json式字符串,往期团购
    public String getGroupBuyDataPast(){
        return groupBuyServiceImp.getGroupBuyDataPast();
    }

    //后台部分
    @RequestMapping("/group_buy_manage")
    public String groupBuyManage() {
        return "group_buy_manage";
    }

    @RequestMapping("/group_buy_manage_past")
    public String groupBuyManagePast() {
        return "group_buy_manage_past";
    }

    @RequestMapping("/group_buy_add")
    public String groupBuyAdd() {
        return "group_buy_add";
    }


    @GetMapping("/group_buy/get_cinema")
    @ResponseBody
    public String getCinema(){//影院信息
        return groupBuyServiceImp.getCinema();
    }

    @PostMapping("/group_buy/add_group_buy")
    @ResponseBody
    public int addGroupBuy(@RequestBody GroupBuyBeans groupBuyBeans) {
        //此处应检查用户权限（是否可以添加该影院的团购信息）
        return groupBuyServiceImp.saveGroupBuy(groupBuyBeans);
    }

    @PostMapping("/group_buy/update_group_buy")
    @ResponseBody
    public int updateGroupBuy(@RequestBody GroupBuyBeans groupBuyBeans) {
        //此处应检查用户权限（是否可以添加该影院的团购信息）
        return groupBuyServiceImp.updateGroupBuy(groupBuyBeans);
    }

    @PostMapping("/group_buy/stop_sell")
    @ResponseBody
    public int stopSell(int[] ids){
        return groupBuyServiceImp.stopSell(ids);
    }
}
