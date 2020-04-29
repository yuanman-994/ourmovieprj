package com.movieprj.services;

import com.movieprj.beans.Hall;

import java.util.List;
import java.util.Map;

public interface HallService {

    public List<Hall> findAllHall();

    public Hall findHallById(Integer hall_id);

    public List<Hall> findHallByCinemaId(Integer cinema_id);

    public int addHall(Map<String,Object> hallMap);

    public int deleteHall(Map<String,List> hall_ids);

    public int updateHall(Map<String,Object> hallMap);

    public Map<String,Object> searchHallByMovies(String search_content);
}
