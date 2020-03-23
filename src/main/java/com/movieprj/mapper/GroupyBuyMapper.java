package com.movieprj.mapper;

import com.movieprj.dao.GroupBuyDao;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GroupyBuyMapper {
    @Select("SELECT * FROM group_buy WHERE group_buy_id =#{id}")
    public GroupBuyDao findById(int id);
}
