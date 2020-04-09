package com.movieprj.services;


import com.movieprj.beans.Seat;

import java.util.List;

public interface SeatService {

    public List<Seat> findSoldSeatByScheduleId(Integer movie_schedule_id);
}
