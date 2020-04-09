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
}
