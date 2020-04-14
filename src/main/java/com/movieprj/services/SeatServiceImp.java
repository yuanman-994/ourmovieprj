package com.movieprj.services;

import com.movieprj.beans.Seat;
import com.movieprj.mapper.SeatMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SeatServiceImp implements SeatService{

    @Resource
    private SeatMapper seatMapper;

    @Override
    public List<Seat> findSoldSeatByScheduleId(Integer movie_schedule_id) {
        return seatMapper.findSoldSeatByScheduleId(movie_schedule_id);
    }

    @Override
    public List<Seat> findAllSeats() {
        return seatMapper.findAllSeats();
    }

    @Override
    public List<Seat> findSeatsByHallId(Integer hall_id) {
        return seatMapper.findSeatsByHallId(hall_id);
    }

    @Override
    public int addSeat(Seat seat) {
        seatMapper.addSeat(seat);
        return 0;
    }

    @Override
    public int deleteSeat(Integer seat_id) {
        seatMapper.deleteSeat(seat_id);
        return 0;
    }

    @Override
    public int updateSeats(Seat seat) {
        seatMapper.updateSeats(seat);
        return 0;
    }

    @Override
    public int batchAddSeats(List<Seat> seatList) {
        seatMapper.batchAddSeats(seatList);
        return 0;
    }

    @Override
    public int batchDeleteSeats(List<Seat> seatList) {
        seatMapper.batchDeleteSeats(seatList);
        return 0;
    }
}
