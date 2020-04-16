package com.movieprj.mapper;

import com.movieprj.beans.Cinema;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CinemaMapper {

    @Select("SELECT * FROM cinema ")
    public List<Cinema> findAllCinema();

    @Select("SELECT * FROM cinema WHERE cinema_id =#{cinema_id}")
    public Cinema findCinemaById(Integer cinema_id);

    @Insert("INSERT INTO cinema(cinema_id,cinema_name,cinema_address,cinema_introduction) " +
            "VALUES (#{cinema_id},#{cinema_name},#{cinema_address},#{cinema_introduction})")
    public int addCinema(Cinema cinema);

    @Delete("DELETE FROM cinema WHERE cinema_id = #{cinema_id}")
    public int deleteCinema(Integer cinema_id);

    @Update("UPDATE cinema set cinema_name = #{cinema_name}," +
            "cinema_address = #{cinema_address}," +
            "cinema_introduction = #{cinema_introduction} " +
            "WHERE cinema_id = #{cinema_id} ")
    public int updateCinema(Cinema cinema);

    @Select("SELECT * FROM cinema")
    public List<Cinema> findAll();
}
