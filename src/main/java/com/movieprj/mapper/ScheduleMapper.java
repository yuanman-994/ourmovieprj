package com.movieprj.mapper;

import com.movieprj.beans.Cinema;
import com.movieprj.beans.MovieSchedule;
import com.movieprj.beans.Movies;
import com.mysql.cj.x.protobuf.MysqlxResultset;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface ScheduleMapper {

    @Select("SELECT * FROM cinema WHERE cinema_id in(" +
            "SELECT distinct cinema_id FROM movie_schedule " +
            "WHERE movie_id =#{movie_id} " +
            "AND (#{date_time} BETWEEN start_sell AND end_sell)"+
            ")")
    public List<Cinema> findCinemaByMovieIdAndDate(Integer movie_id,String date_time);

    @Select("SELECT * FROM movie_schedule WHERE cinema_id = #{cinema_id} " +
            "AND movie_id = #{movie_id} " +
            "AND #{date_time} BETWEEN start_sell AND end_sell")
    public List<MovieSchedule> findScheduleByMovieIdAndCinemaIdAndDate(Integer cinema_id,Integer movie_id,String date_time);

    @Select("SELECT * FROM movie_schedule WHERE movie_schedule_id = #{movie_schedule_id} ")
    public MovieSchedule findScheduleById(Integer movie_schedule_id);

    @Select("SELECT * FROM movie_schedule ")
    @Results({
            @Result(id=true,property = "movie_schedule_id",column = "movie_schedule_id"),
            @Result(property = "cinema_id",column = "cinema_id"),
            @Result(property = "hall_id",column = "hall_id"),
            @Result(property = "movie_id",column = "movie_id"),
            @Result(property = "show_type",column = "show_type"),
            @Result(property = "price",column = "price"),
            @Result(property = "start_sell",column = "start_sell"),
            @Result(property = "end_sell",column = "end_sell"),
            @Result(property = "movie",column = "movie_id",one=@One(select="com.movieprj.mapper.MoviesMapper.selectMoviesById",fetchType = FetchType.EAGER)),
            @Result(property = "cinema",column = "cinema_id",one=@One(select="com.movieprj.mapper.CinemaMapper.findCinemaById",fetchType = FetchType.EAGER)),
            @Result(property = "hall",column = "hall_id",one=@One(select="com.movieprj.mapper.HallMapper.findHallById",fetchType = FetchType.EAGER)),
    })
    public List<MovieSchedule> findAllSchedule();

    @Select("SELECT * FROM movie_schedule WHERE movie_schedule_id = #{movie_schedule_id}")
    @Results({
            @Result(id=true,property = "movie_schedule_id",column = "movie_schedule_id"),
            @Result(property = "cinema_id",column = "cinema_id"),
            @Result(property = "hall_id",column = "hall_id"),
            @Result(property = "movie_id",column = "movie_id"),
            @Result(property = "show_type",column = "show_type"),
            @Result(property = "price",column = "price"),
            @Result(property = "start_sell",column = "start_sell"),
            @Result(property = "end_sell",column = "end_sell"),
            @Result(property = "movie",column = "movie_id",one=@One(select="com.movieprj.mapper.MoviesMapper.selectMoviesById",fetchType = FetchType.EAGER)),
            @Result(property = "cinema",column = "cinema_id",one=@One(select="com.movieprj.mapper.CinemaMapper.findCinemaById",fetchType = FetchType.EAGER)),
            @Result(property = "hall",column = "hall_id",one=@One(select="com.movieprj.mapper.HallMapper.findHallById",fetchType = FetchType.EAGER)),
    })
    public MovieSchedule findScheduleById1(Integer movie_schedule_id);


    @Insert("INSERT INTO movie_schedule (movie_schedule_id,cinema_id,hall_id,movie_id,show_type,price,start_sell,end_sell) " +
            "VALUES (#{movie_schedule_id},#{cinema_id},#{hall_id},#{movie_id},#{show_type},#{price},#{start_sell},#{end_sell})")
    public int addSchedule(MovieSchedule movieSchedule);

    @Delete("DELETE FROM movie_schedule WHERE movie_schedule_id = #{movie_schedule_id}")
    public int deleteSchedule(Integer movie_schedule_id);

    @Update("UPDATE movie_schedule set "+
            "cinema_id = #{cinema_id},"+
            "hall_id = #{hall_id},"+
            "movie_id = #{movie_id},"+
            "show_type = #{show_type},"+
            "price = #{price},"+
            "start_sell = #{start_sell},"+
            "end_sell = #{end_sell} "+
            "WHERE movie_schedule_id = #{movie_schedule_id}"
    )
    public int updateSchedule(MovieSchedule movieSchedule);

    @SelectProvider(type= searchProvider.class ,method="search")
    @Results({
            @Result(id=true,property = "movie_schedule_id",column = "movie_schedule_id"),
            @Result(property = "cinema_id",column = "cinema_id"),
            @Result(property = "hall_id",column = "hall_id"),
            @Result(property = "movie_id",column = "movie_id"),
            @Result(property = "show_type",column = "show_type"),
            @Result(property = "price",column = "price"),
            @Result(property = "start_sell",column = "start_sell"),
            @Result(property = "end_sell",column = "end_sell"),
            @Result(property = "movie",column = "movie_id",one=@One(select="com.movieprj.mapper.MoviesMapper.selectMoviesById",fetchType = FetchType.EAGER)),
            @Result(property = "cinema",column = "cinema_id",one=@One(select="com.movieprj.mapper.CinemaMapper.findCinemaById",fetchType = FetchType.EAGER)),
            @Result(property = "hall",column = "hall_id",one=@One(select="com.movieprj.mapper.HallMapper.findHallById",fetchType = FetchType.EAGER)),
    })
    public List<MovieSchedule> searchSchedule(@Param(value = "moviesList") List<Movies> moviesList, @Param(value = "select_date") String select_date,
                                              @Param(value = "select_cinema") String select_cinema, @Param(value = "select_hall") String select_hall);

    class searchProvider {
        /* 条件组合查询 */
        public String search(Map<String,Object> param) {
            List<Movies> moviesList = (List<Movies>)param.get("moviesList");
            String select_date = param.get("select_date").toString();
            String select_cinema = param.get("select_cinema").toString();
            String select_hall = param.get("select_hall").toString();
            int[] movie_ids = new int[100];
            int i;
            for( i =0 ; i<moviesList.size();i++){
                movie_ids[i] = moviesList.get(i).getMovie_id();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM movie_schedule m WHERE ");
            if(movie_ids.length>0){
                sb.append("m.movie_id in ( ");
                for(int n=0; n<i;n++){
                    sb.append(movie_ids[n]);
                    if(n==i-1){
                        sb.append(") AND ");
                    }
                    else{
                        sb.append(",");
                    }
                }
            }else {
                sb.append("1=1 AND ");
            }
            if(!select_date.equals("datenone")){
                Date date=new Date();
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                if(select_date.equals("week")) {
                    c.add(Calendar.DAY_OF_MONTH,7);
                    Date date1 = c.getTime();
                    sb.append("m.start_sell >= "+"\""+simpleDate.format(date)+"\"");
                    sb.append(" AND m.start_sell <= "+"\""+simpleDate.format(date1)+"\"");
                    sb.append(" AND ");
                }
                else if(select_date.equals("month")) {
                    c.add(Calendar.MONTH,1);
                    Date date2 = c.getTime();
                    sb.append("m.start_sell >= "+"\""+simpleDate.format(date)+"\"");
                    sb.append(" AND m.start_sell <= "+"\""+simpleDate.format(date2)+"\"");
                    sb.append(" AND ");
                }
            } else {
                sb.append("1=1 AND ");
            }
            if(!select_cinema.equals("cinemanone")){
                Integer cinema_id = Integer.valueOf(select_cinema);
                sb.append("m.cinema_id = "+cinema_id);
                sb.append(" AND ");
               }
                else {
                sb.append("1=1 AND");
            }

            if(!select_hall.equals("hallnone")&&!select_hall.equals("")){
                Integer hall_id = Integer.valueOf(select_hall);
                sb.append("m.hall_id = "+hall_id);
            }
            else {
                sb.append("1=1");
            }
            return sb.toString();
        }
    }

}
