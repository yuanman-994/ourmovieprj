package com.movieprj.services.groupbuy;

public interface GroupBuyService {
    public String getGroupBuyDataNow();//    当期团购，返回json风式字符串
    public String getGroupBuyDataPast();//   往期团购，返回json式字符串
}
