package com.movieprj.mapper;

import com.movieprj.beans.GroupBuyBeans;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupBuyMapper {
    @Select("SELECT * FROM group_buy WHERE group_buy_id =#{id}")
    public GroupBuyBeans findById(int id);//以id查询单条团购

    @Select("SELECT * FROM group_buy")
    public List<GroupBuyBeans> getAllGroupBuyData();//查询所有团购

    @Select("SELECT * FROM group_buy WHERE end_time > CURRENT_TIMESTAMP()")
    public List<GroupBuyBeans> getGroupBuyDataNOW();//查询当期团购

    @Select("SELECT * FROM group_buy WHERE end_time < CURRENT_TIMESTAMP()")
    public List<GroupBuyBeans> getGroupBuyDataPast();//查询往期团购

    @Select("SELECT cinema_name FROM cinema WHERE cinema_id =#{id}")
    public String getCinemaNameById(int id);
}
