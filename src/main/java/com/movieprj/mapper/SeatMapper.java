package com.movieprj.mapper;

import com.movieprj.beans.Seat;
import org.apache.ibatis.annotations.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Mapper
public interface SeatMapper {

    @Select("SELECT * FROM seat WHERE seat_id in(" +
            "SELECT distinct seat_id FROM ticket_to_sell " +
            "WHERE movie_schedule_id =#{movie_schedule_id} " +
            "AND status = 1 "+
            ")")
    public List<Seat> findSoldSeatByScheduleId(Integer movie_schedule_id);

    @Select("SELECT * FROM seat ")
    public List<Seat> findAllSeats();

    @Select("SELECT * FROM seat WHERE hall_id = #{hall_id} ")
    public List<Seat> findSeatsByHallId(Integer hall_id);

    @Insert("INSERT INTO seat (seat_id,hall_id,loc_x,loc_y) " +
            "VALUES (#{seat_id},#{hall_id},#{loc_x},#{loc_y})")
    public int addSeat(Seat seat);

    @Delete("DELETE FROM seat WHERE seat_id = #{seat_id}")
    public int deleteSeat(Integer seat_id);

    @Update("UPDATE seat set hall_id = #{hall_id}, " +
            "loc_x = #{loc_x}," +
            "loc_y = #{loc_y} " +
            "WHERE seat_id = #{seat_id} ")
    public int updateSeats(Seat seat);


/*    @Insert({
            "<script>",
            "insert into seat(seat_id,hall_id,loc_x,loc_y) values ",
            "<foreach collection='seatList' item='Seat' index='index' separator=','>",
            "(#{Seat.seat_id}, #{Seat.hall_id}, #{Seat.loc_x},#{Seat.loc_y})",
            " </foreach>",
            "</script>"
    })
    int batchAddSeats(@Param(value = "seatList") List<Seat> seatList);//批量增加座位信息*/


    @InsertProvider(type = Provider.class, method = "batchAdd")//批量增加座位信息
    public int batchAddSeats(List<Seat> seatList);

    @DeleteProvider(type = Provider.class, method = "batchDelete")//批量删除座位信息
    public int batchDeleteSeats(List<Seat> seatList);

    class Provider {
        /* 批量插入 */
        public String batchAdd(Map map) {
            List<Seat> seatList = (List<Seat>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO student (seat_id,hall_id,loc_x,loc_y) VALUES ");
            MessageFormat mf = new MessageFormat(
                    "(#'{'list[{0}].seat_id}, #'{'list[{0}].hall_id}, #'{'list[{0}].loc_x}, #'{'list[{0}].loc_y})"
            );

            for (int i = 0; i < seatList.size(); i++) {
                sb.append(mf.format(new Object[] {i}));
                if (i < seatList.size() - 1)
                    sb.append(",");
            }
            return sb.toString();
        }

        /* 批量删除 */
        public String batchDelete(Map map) {
            List<Seat> seatsList = (List<Seat>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM student WHERE id IN (");
            for (int i = 0; i < seatsList.size(); i++) {
                sb.append("'").append(seatsList.get(i).getSeat_id()).append("'");
                if (i < seatsList.size() - 1)
                    sb.append(",");
            }
            sb.append(")");
            return sb.toString();
        }
    }

}
