package com.movieprj.mapper;

import com.movieprj.beans.Movies;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;


import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

//@Repository
@Mapper
public interface MoviesMapper {
    @Select("SELECT * FROM movie ")
    public List<Movies> findAll();

    @Select("SELECT * FROM movie WHERE movie_id =#{movie_id}")
    public Movies selectMoviesById(Integer movie_id);

    @Select("SELECT * FROM movie WHERE movie_id =#{movie_id}")
    @Results({
            @Result(id=true,property = "movie_id",column = "movie_id"),
            @Result(property = "cover",column = "cover"),
            @Result(property = "movie_name",column = "movie_name"),
            @Result(property = "director",column = "director"),
            @Result(property = "main_actor",column = "main_actor"),
            @Result(property = "intro",column = "intro"),
            @Result(property = "rank",column = "rank"),
            @Result(property = "release_date",column = "release_date"),
            @Result(property = "onshow",column = "onshow"),
            @Result(property = "comment_permission",column = "comment_permission"),
            @Result(property = "moviesTypesList",column = "movie_id",many=@Many(select="com.movieprj.mapper.MoviesTypesMapper.findTypesByMovieId",fetchType = FetchType.EAGER)),
    })
    public Movies selectMoviesWithTypesById(Integer movie_id);

    @Select("SELECT * FROM movie ")
    @Results({
            @Result(id=true,property = "movie_id",column = "movie_id"),
            @Result(property = "cover",column = "cover"),
            @Result(property = "movie_name",column = "movie_name"),
            @Result(property = "director",column = "director"),
            @Result(property = "main_actor",column = "main_actor"),
            @Result(property = "intro",column = "intro"),
            @Result(property = "rank",column = "rank"),
            @Result(property = "release_date",column = "release_date"),
            @Result(property = "onshow",column = "onshow"),
            @Result(property = "comment_permission",column = "comment_permission"),
            @Result(property = "moviesTypesList",column = "movie_id",many=@Many(select="com.movieprj.mapper.MoviesTypesMapper.findTypesByMovieId",fetchType = FetchType.EAGER)),
    })
    public List<Movies> findAllWithTypes();

    //@Select("SELECT * FROM movie m WHERE m.movie_name like CONCAT('%',#{movie_name},'%')")
    @SelectProvider(type = selectProvider.class, method = "search")
    @Results({
            @Result(id=true,property = "movie_id",column = "movie_id"),
            @Result(property = "cover",column = "cover"),
            @Result(property = "movie_name",column = "movie_name"),
            @Result(property = "director",column = "director"),
            @Result(property = "main_actor",column = "main_actor"),
            @Result(property = "intro",column = "intro"),
            @Result(property = "rank",column = "rank"),
            @Result(property = "release_date",column = "release_date"),
            @Result(property = "onshow",column = "onshow"),
            @Result(property = "comment_permission",column = "comment_permission"),
            @Result(property = "moviesTypesList",column = "movie_id",many=@Many(select="com.movieprj.mapper.MoviesTypesMapper.findTypesByMovieId",fetchType = FetchType.EAGER)),
    })
    public List<Movies> searchMovies(@Param(value = "movie_name") String movie_name,@Param(value = "select_date") String select_date,@Param(value = "select_status") String select_status);

    @Select("SELECT * FROM movie WHERE onshow =1")
    public  List<Movies> selectMoviesByOnshow(Integer onshow);

    @Insert("INSERT INTO movie (movie_id,cover,movie_name,director,main_actor,intro,rank,release_date,onshow,comment_permission) " +
            "VALUES (#{movie_id},#{cover},#{movie_name},#{director},#{main_actor},#{intro},#{rank},#{release_date},#{onshow},#{comment_permission})")
    public int addMovies(Movies movies);

    @Delete("DELETE FROM movie WHERE movie_id = #{movie_id}")
    public int deleteMovies(Integer movie_id);

    @Update("UPDATE movie set movie_name = #{movie_name}, " +
            "cover = #{cover}," +
            "director = #{director}," +
            "main_actor = #{main_actor}," +
            "intro = #{intro},"+
            "rank = #{rank},"+
            "release_date = #{release_date},"+
            "onshow = #{onshow}, "+
            "comment_permission = #{comment_permission} "+
            "WHERE movie_id = #{movie_id} ")
    public int updateMovies(Movies movies);

    @Select("SELECT * FROM movie m WHERE m.movie_name like CONCAT('%',#{movie_name},'%')")
    public List<Movies> searchMoviesByName(String movie_name);

    class selectProvider {
        /* 条件组合查询 */
        public String search(Map<String,Object> param) {
            String movie_name = param.get("movie_name").toString();
            String select_date = param.get("select_date").toString();
            String select_status = param.get("select_status").toString();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM movie m WHERE ");
            if(movie_name!=null){
                sb.append("m.movie_name like CONCAT('%',#{movie_name},'%') AND ");
            }
            if(!select_date.equals("datenone")){
                Date date=new Date();
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                if(select_date.equals("week")) {
                    c.add(Calendar.DAY_OF_MONTH,-7);
                    Date date1 = c.getTime();
                    sb.append("m.release_date <= "+"\""+simpleDate.format(date)+"\"");
                    sb.append(" AND m.release_date >= "+"\""+simpleDate.format(date1)+"\"");
                    sb.append(" AND ");
                }
                else if(select_date.equals("month")) {
                    c.add(Calendar.MONTH,-1);
                    Date date2 = c.getTime();
                    sb.append("m.release_date <= "+"\""+simpleDate.format(date)+"\"");
                    sb.append(" AND m.release_date >= "+"\""+simpleDate.format(date2)+"\"");
                    sb.append(" AND ");
                }
            } else {
                sb.append("1=1 AND ");
            }
            if(!select_status.equals("statusnone")){
                if(select_status.equals("onshow")){
                    sb.append("m.onshow = 1");
                }
                else if (select_status.equals("upcoming")){
                    sb.append("m.onshow = 0");
                }
            } else {
                sb.append("1=1");
            }

            return sb.toString();
        }
    }
}
