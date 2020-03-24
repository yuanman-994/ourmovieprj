package com.movieprj.mapper;

import com.movieprj.beans.GroupBuyBeans;

import org.apache.ibatis.annotations.*;

@Mapper
public interface GroupyBuyMapper {
    @Select("SELECT * FROM group_buy WHERE group_buy_id =#{id}")
    public GroupBuyBeans findById(int id);
}
