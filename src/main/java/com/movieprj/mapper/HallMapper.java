package com.movieprj.mapper;

import com.movieprj.beans.Hall;
import com.movieprj.beans.Movies;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HallMapper {

    @Select("SELECT * FROM hall WHERE hall_id =#{hall_id}")
    public Hall findHallById(Integer hall_id);
}
