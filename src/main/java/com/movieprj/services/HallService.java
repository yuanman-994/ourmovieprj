package com.movieprj.services;

import com.movieprj.beans.Hall;

import java.util.List;
import java.util.Map;

public interface HallService {

    public List<Hall> findAllHall();

    public Hall findHallById(Integer hall_id);

    public List<Hall> findHallByCinemaId(Integer cinema_id);

    public int addHall(Map<String,String> hallMap);

    public int deleteHall(List<Integer> hall_ids);

    public int updateHall(Map<String,String> hallMap);
}
