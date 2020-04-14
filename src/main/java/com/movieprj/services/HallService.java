package com.movieprj.services;

import com.movieprj.beans.Hall;

public interface HallService {

    public Hall findHallById(Integer hall_id);

    public int addHall(Hall hall);

    public int deleteHall(Integer hall_id);

    public int updateHall(Hall hall);
}
