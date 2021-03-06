package com.movieprj.mapper;

import com.movieprj.beans.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TicketMapper {

    @Insert("INSERT INTO ticket_to_sell (movie_schedule_id,seat_id,status,order_id) " +
            "VALUES (#{movie_schedule_id},#{seat_id},#{status},#{order_id})")
    @Options(useGeneratedKeys=true, keyProperty="ticket_id", keyColumn="ticket_id")
    public int addTicket(Ticket ticket);

    @Select("SELECT * FROM ticket_to_sell WHERE order_id =#{order_id}")
    public List<Ticket> findTicketsByOrderId(Integer order_id);

    @Delete("DELETE FROM ticket_to_sell WHERE order_id = #{order_id}")
    public int deleteTicket(Integer order_id);
}
