package com.movieprj.mapper;

import com.movieprj.beans.Article;
import com.movieprj.beans.GroupBuyBeans;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupBuyMapper {
    @Select("SELECT * FROM group_buy WHERE group_buy_id =#{id}")
    public GroupBuyBeans findById(int id);//以id查询单条团购

    @Select("SELECT * FROM group_buy")
    public List<GroupBuyBeans> getAllGroupBuyData();//查询所有团购

    @Select("SELECT * FROM group_buy WHERE end_sell > CURRENT_TIMESTAMP()")
    public List<GroupBuyBeans> getGroupBuyDataNOW();//查询当期团购

    @Select("SELECT * FROM group_buy WHERE end_sell < CURRENT_TIMESTAMP()")
    public List<GroupBuyBeans> getGroupBuyDataPast();//查询往期团购

    @Select("SELECT cinema_name FROM cinema WHERE cinema_id =#{id}")
    public String getCinemaNameById(int id);

    @Insert("INSERT INTO group_buy(cinema_id,start_time,end_time,start_sell,end_sell,now_sales,max_sales,price)" +
            "VALUES (#{cinema_id},#{start_time},#{end_time},#{start_sell},#{end_sell},#{now_sales},#{max_sales},#{price})")
    @Options(useGeneratedKeys = true, keyProperty = "group_buy_id", keyColumn = "group_buy_id")
    public void insertGroupBuy(GroupBuyBeans groupBuyBeans);


    @Update("UPDATE group_buy SET start_sell=#{start_sell},end_sell=#{end_sell},max_sales=#{max_sales} WHERE group_buy_id=#{group_buy_id}")
    public void updateGroupBuy(GroupBuyBeans groupBuyBeans);

    @Update("UPDATE group_buy SET end_sell = CURRENT_TIMESTAMP() WHERE group_buy_id=#{id}")
    public void stopSellById(int id);
}
