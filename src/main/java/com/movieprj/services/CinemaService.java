package com.movieprj.services;

import com.movieprj.beans.Cinema;

import java.util.List;
import java.util.Map;

public interface CinemaService {

    public List<Cinema> findAllCinema();
    public Cinema findCinemaById(Integer cinema_id);
    public int addCinema(Map<String,Object> cinemaMap);
    public int deleteCinema(Map<String,List> cinema_ids);
    public int updateCinema(Map<String,Object> cinemaMap);
    public List<Cinema> findCinemaByName(String movie_name);
}
