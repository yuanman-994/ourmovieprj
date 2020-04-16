package com.movieprj.services;

import com.movieprj.beans.Cinema;

import java.util.List;
import java.util.Map;

public interface CinemaService {

    public List<Cinema> findAllCinema();
    public Cinema findCinemaById(Integer cinema_id);
    public int addCinema(Map<String,String> cinemaMap);
    public int deleteCinema(List<Integer> cinema_ids);
    public int updateCinema(Map<String,String> cinemaMap);
}
