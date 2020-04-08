package com.movieprj.services.groupbuy;

import org.springframework.stereotype.Service;

@Service
//对应页面中实际展示的团购内容
public class GroupBuyPageComponent {
    public int group_buy_id;//团购id
    public String start_time;//团购票开始有效期
    public String end_time;//团购票结束有效期
    public float price;//价格
    public int now_sales;//销量
    public int max_sales;//最大销量
    public String start_sell;//开售时间
    public String end_sell;//结束售票时间
    public String cinema_name;//对应影院
    public int cinema_id;//影院id

    @Override
    public String toString() {
        return "GroupBuyPageComponent{" +
                "group_buy_id=" + group_buy_id +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", price=" + price +
                ", now_sales=" + now_sales +
                ", max_sales=" + max_sales +
                ", start_sell='" + start_sell + '\'' +
                ", end_sell='" + end_sell + '\'' +
                ", cinema_name='" + cinema_name + '\'' +
                ", cinema_id=" + cinema_id +
                '}';
    }

}
