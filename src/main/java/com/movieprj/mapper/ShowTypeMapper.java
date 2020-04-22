package com.movieprj.mapper;

import com.movieprj.beans.ShowType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShowTypeMapper {

    @Select("SELECT * FROM movie_show_type ")
    public List<ShowType> findAll();

    @Insert("INSERT INTO movie_show_type (show_type) " +
            "VALUES (#{show_type})")
    public int addShowType(ShowType show_type);

    @Delete("DELETE FROM movie_show_type WHERE show_type = #{show_type}")
    public int deleteShowType(ShowType showType);

    @Update("Update movie_show_type set show_type = #{new_show_type} WHERE show_type = #{old_show_type}")
    public int updateShowType(String new_show_type,String old_show_type);
}
