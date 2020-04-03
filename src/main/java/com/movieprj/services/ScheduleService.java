package com.movieprj.services;

import com.movieprj.beans.Cinema;
import com.movieprj.beans.MovieSchedule;

import java.util.List;

public interface ScheduleService {

    public List<Cinema> findCinemaByMovieIdAndDate(Integer movie_id, String date_time);

    public List<MovieSchedule> findScheduleByMovieIdAndCinemaIdAndDate(Integer cinema_id, Integer movie_id, String date_time);
}
