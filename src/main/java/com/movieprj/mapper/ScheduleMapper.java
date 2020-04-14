package com.movieprj.mapper;

import com.movieprj.beans.Cinema;
import com.movieprj.beans.MovieSchedule;
import com.mysql.cj.x.protobuf.MysqlxResultset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    @Select("SELECT * FROM cinema WHERE cinema_id in(" +
            "SELECT distinct cinema_id FROM movie_schedule " +
            "WHERE movie_id =#{movie_id} " +
            "AND (#{date_time} BETWEEN start_sell AND end_sell)"+
            ")")
    public List<Cinema> findCinemaByMovieIdAndDate(Integer movie_id,String date_time);

    @Select("SELECT * FROM movie_schedule WHERE cinema_id = #{cinema_id} " +
            "AND movie_id = #{movie_id} " +
            "AND #{date_time} BETWEEN start_sell AND end_sell")
    public List<MovieSchedule> findScheduleByMovieIdAndCinemaIdAndDate(Integer cinema_id,Integer movie_id,String date_time);

    @Select("SELECT * FROM movie_schedule WHERE movie_schedule_id = #{movie_schedule_id} ")
    public MovieSchedule findScheduleById(Integer movie_schedule_id);
}
