package com.movieprj.services;

import com.movieprj.beans.Cinema;
import com.movieprj.beans.MovieSchedule;
import com.movieprj.mapper.ScheduleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScheduleServiceImp implements ScheduleService {

    @Resource
    private ScheduleMapper scheduleMapper;

    @Override
    public List<Cinema> findCinemaByMovieIdAndDate(Integer movie_id, String date_time) {
        return scheduleMapper.findCinemaByMovieIdAndDate(movie_id,date_time);
    }

    @Override
    public List<MovieSchedule> findScheduleByMovieIdAndCinemaIdAndDate(Integer cinema_id, Integer movie_id, String date_time) {
        return scheduleMapper.findScheduleByMovieIdAndCinemaIdAndDate(cinema_id,movie_id,date_time);
    }

    @Override
    public MovieSchedule findScheduleById(Integer movie_schedule_id) {
        return scheduleMapper.findScheduleById(movie_schedule_id);
    }

}
