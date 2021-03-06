package com.movieprj.mapper;

import com.movieprj.beans.Hall;
import com.movieprj.beans.Movies;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HallMapper {

    @Select("SELECT * FROM hall ")
    public List<Hall> findAllHall();

    @Select("SELECT * FROM hall WHERE hall_id =#{hall_id}")
    public Hall findHallById(Integer hall_id);

    @Select("SELECT * FROM hall WHERE cinema_id =#{cinema_id}")
    public List<Hall> findHallByCinemaId(Integer cinema_id);

    @Insert("INSERT INTO hall(hall_id,cinema_id,number_of_seats,seat_map,hall_name) " +
            "VALUES (#{hall_id},#{cinema_id},#{number_of_seats},#{seat_map},#{hall_name})")
    public int addHall(Hall hall);

    @Delete("DELETE FROM hall WHERE hall_id = #{hall_id}")
    public int deleteHall(Integer hall_id);

    @Delete("DELETE FROM hall WHERE cinema_id = #{cinema_id}")
    public int deleteHallByCinemaId(Integer cinema_id);

    @Update("UPDATE hall set cinema_id = #{cinema_id}," +
            "number_of_seats = #{number_of_seats}," +
            "seat_map = #{seat_map}," +
            "hall_name = #{hall_name} " +
            "WHERE hall_id = #{hall_id} ")
    public int updateHall(Hall hall);


}
