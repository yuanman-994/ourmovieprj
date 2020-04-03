package com.movieprj.mapper;

import com.movieprj.beans.Cinema;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CinemaMapper {

    @Select("SELECT * FROM cinema WHERE cinema_id =#{cinema_id}")
    public Cinema findCinemaById(Integer cinema_id);

}
