package com.movieprj.mapper;

import com.movieprj.beans.Movies;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;


import java.util.List;

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
            @Result(property = "movie_name",column = "movie_name"),
            @Result(property = "director",column = "director"),
            @Result(property = "main_actor",column = "main_actor"),
            @Result(property = "intro",column = "intro"),
            @Result(property = "rank",column = "rank"),
            @Result(property = "release_date",column = "release_date"),
            @Result(property = "onshow",column = "onshow"),
            @Result(property = "moviesTypesList",column = "movie_id",many=@Many(select="com.movieprj.mapper.MoviesTypesMapper.findTypesByMovieId",fetchType = FetchType.EAGER)),
    })
    public Movies selectMoviesWithTypesById(Integer movie_id);

    @Select("SELECT * FROM movie WHERE onshow =1")
    public  List<Movies> selectMoviesByOnshow(Integer onshow);

    @Insert("INSERT INTO movie (movie_id,movie_name,director,main_actor,intro,rank,release_date,onshow) " +
            "VALUES (#{movie_id},#{movie_name},#{director},#{main_actor},#{intro},#{rank},#{release_date},#{onshow})")
    public int addMovies(Movies movies);

    @Delete("DELETE FROM movie WHERE movie_id = #{movie_id}")
    public int deleteMovies(Integer movie_id);

    @Update("UPDATE movie set movie_name = #{movie_name}, " +
            "director = #{director}," +
            "main_actor = #{main_actor}," +
            "intro = #{intro},"+
            "rank = #{rank},"+
            "release_date = #{release_date},"+
            "onshow = #{onshow} "+
            "WHERE movie_id = #{movie_id} ")
    public int updateMovies(Movies movies);

}
