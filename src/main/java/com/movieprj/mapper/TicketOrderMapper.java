package com.movieprj.mapper;

import com.movieprj.beans.TicketOrder;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;



@Mapper
public interface TicketOrderMapper {

    @Select("SELECT * FROM ticket_order WHERE order_id =#{order_id}")
    @Results({
            @Result(id=true,property = "order_id",column = "order_id"),
            @Result(property = "user_id",column = "user_id"),
            @Result(property = "movie_schedule_id",column = "movie_schedule_id"),
            @Result(property = "time",column = "time"),
            @Result(property = "price",column = "price"),
            @Result(property = "pay_way",column = "pay_way"),
            @Result(property = "ticketList",column = "order_id",many=@Many(select="com.movieprj.mapper.TicketMapper.findTicketsByOrderId",fetchType = FetchType.EAGER)),
    })
    public TicketOrder findTicketOrderWithTicketsById(Integer order_id);


    @Insert("INSERT INTO ticket_order (order_id,user_id,movie_schedule_id,time,price,pay_way) " +
            "VALUES (#{order_id},#{user_id},#{movie_schedule_id},#{time},#{price},#{pay_way})")
    @Options(useGeneratedKeys=true, keyProperty="order_id", keyColumn="order_id")
    public int addTicketOrder(TicketOrder ticketOrder);

    @Delete("DELETE FROM ticket_order WHERE order_id = #{order_id}")
    public int deleteTicketOrder(Integer order_id);

    @Update("UPDATE ticket_order set pay_way = #{pay_way} WHERE order_id = #{order_id}")
    public int updateTicketOrderStatus(Integer order_id,String pay_way);
}
