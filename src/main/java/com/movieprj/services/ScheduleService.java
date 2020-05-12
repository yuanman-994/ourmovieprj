package com.movieprj.services;

import com.movieprj.beans.Cinema;
import com.movieprj.beans.MovieSchedule;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    public List<Cinema> findCinemaByMovieIdAndDate(Integer movie_id, String date_time);

    public List<MovieSchedule> findScheduleByMovieIdAndCinemaIdAndDate(Integer cinema_id, Integer movie_id, String date_time);

    public List<Cinema> findCinemaByMovieIdAndDateAndName(Integer movie_id,String date_time,String cinema_name);

    public MovieSchedule findScheduleById(Integer movie_schedule_id);

    public List<MovieSchedule> findAllSchedule();

    public int addSchedule(MovieSchedule movieSchedule);

    public MovieSchedule findScheduleById1(Integer movie_schedule_id);

    public int deleteSchedules(Map<String,List> movie_schedule_ids);

    public int updateSchedule(MovieSchedule movieSchedule);

    public Map<String,Object> searchSchedule(String movie_name,String search_date,String search_cinema,String search_hall);
}
