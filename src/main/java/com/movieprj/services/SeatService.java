package com.movieprj.services;


import com.movieprj.beans.Seat;

import java.util.List;

public interface SeatService {

    public List<Seat> findSoldSeatByScheduleId(Integer movie_schedule_id);

    public List<Seat> findAllSeats();

    public List<Seat> findSeatsByHallId(Integer hall_id);

    public int addSeat(Seat seat);

    public int deleteSeat(Integer seat_id);

    public int updateSeats(Seat seat);

    public int batchAddSeats(List<Seat> seatList);

    public int batchDeleteSeats(List<Seat> seatList);
}
