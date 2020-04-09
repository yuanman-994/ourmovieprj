package com.movieprj.mapper;

import com.movieprj.beans.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SeatMapper {

    @Select("SELECT * FROM seat WHERE seat_id in(" +
            "SELECT distinct seat_id FROM ticket_to_sell " +
            "WHERE movie_schedule_id =#{movie_schedule_id} " +
            "AND status = 1 "+
            ")")
    public List<Seat> findSoldSeatByScheduleId(Integer movie_schedule_id);
}
