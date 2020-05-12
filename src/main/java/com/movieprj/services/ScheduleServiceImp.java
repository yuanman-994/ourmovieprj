package com.movieprj.services;

import com.movieprj.beans.Cinema;
import com.movieprj.beans.MovieSchedule;
import com.movieprj.beans.Movies;
import com.movieprj.mapper.MoviesMapper;
import com.movieprj.mapper.ScheduleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImp implements ScheduleService {

    @Resource
    private ScheduleMapper scheduleMapper;

    @Resource
    private MoviesMapper moviesMapper;

    @Override
    public List<Cinema> findCinemaByMovieIdAndDate(Integer movie_id, String date_time) {
        return scheduleMapper.findCinemaByMovieIdAndDate(movie_id,date_time);
    }

    @Override
    public List<MovieSchedule> findScheduleByMovieIdAndCinemaIdAndDate(Integer cinema_id, Integer movie_id, String date_time) {
        return scheduleMapper.findScheduleByMovieIdAndCinemaIdAndDate(cinema_id,movie_id,date_time);
    }

    @Override
    public List<Cinema> findCinemaByMovieIdAndDateAndName(Integer movie_id, String date_time, String cinema_name) {
        return scheduleMapper.findCinemaByMovieIdAndDateAndName(movie_id, date_time, cinema_name);
    }

    @Override
    public MovieSchedule findScheduleById(Integer movie_schedule_id) {
        return scheduleMapper.findScheduleById(movie_schedule_id);
    }

    @Override
    public List<MovieSchedule> findAllSchedule() {
        return scheduleMapper.findAllSchedule();
    }

    @Override
    public int addSchedule(MovieSchedule movieSchedule) {
        try {
          scheduleMapper.addSchedule(movieSchedule);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public MovieSchedule findScheduleById1(Integer movie_schedule_id) {
        try {
            return scheduleMapper.findScheduleById1(movie_schedule_id);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int deleteSchedules(Map<String, List> movie_schedule_ids) {
        try {
            List ids = movie_schedule_ids.get("schedule_ids");
            for (int i = 0; i < ids.size(); i++) {
                scheduleMapper.deleteSchedule(Integer.valueOf(ids.get(i).toString()));
            }
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateSchedule(MovieSchedule movieSchedule) {
        try{
            scheduleMapper.updateSchedule(movieSchedule);
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public Map<String,Object> searchSchedule(String movie_name, String search_date, String search_cinema, String search_hall) {
        List<Movies> moviesList =  moviesMapper.searchMoviesByName(movie_name);
        List<MovieSchedule> scheduleList = scheduleMapper.searchSchedule(moviesList,search_date,search_cinema,search_hall);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("scheduleList",scheduleList);
        return map;
    }

}
