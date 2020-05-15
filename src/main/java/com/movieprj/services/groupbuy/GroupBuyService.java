package com.movieprj.services.groupbuy;

import com.movieprj.beans.GroupBuyBeans;

public interface GroupBuyService {
    public String getGroupBuyDataNow();//    当期团购，返回json式字符串
    public String getGroupBuyDataPast();//   往期团购，返回json式字符串
    public String getCinema();
    public int saveGroupBuy(GroupBuyBeans groupBuyBeans);
    public int updateGroupBuy(GroupBuyBeans groupBuyBeans);
    public int stopSell(int[] ids);
    public String addTempOrder(int id,int num,int user_id);
}
