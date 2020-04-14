package com.movieprj.mapper;

import com.movieprj.beans.MoviesTypes;
import org.apache.ibatis.annotations.*;
import org.hibernate.validator.constraints.EAN;

import java.util.List;

@Mapper
public interface MoviesTypesMapper {

    @Select("SELECT * FROM movie_type WHERE movie_id = #{movie_id}")
    public List<MoviesTypes> findTypesByMovieId(Integer movie_id);

    @Select("SELECT distinct type_name FROM movie_type")
    public List<String> findTypesName();//用于显示已有全部的类型名单

    @Insert("INSERT INTO movie_type(movie_id,type_name) " +
            "VALUES (#{movie_id},#{type_name})")
    @Options(useGeneratedKeys=true, keyProperty="movie_type_id", keyColumn="movie_type_id")
    public int addMoviesType(MoviesTypes moviesTypes);//用于类型管理的类型添加

    @Insert("INSERT INTO movie_type(movie_id,type_name) " +
            "VALUES (#{movie_id},#{type_name})")
    @Options(useGeneratedKeys=true, keyProperty="movie_type_id", keyColumn="movie_type_id")
    public int addMoviesType1(MoviesTypes moviesTypes);//用于添加电影时的类型的设置

    @Update("UPDATE movie_type SET type_name=#{new_type_name} WHERE type_name=#{old_type_name}")
    public int updateMoviesTypes(String new_type_name,String old_type_name);//用于类型管理的更新(所有类型名都同时更新)

    @Update("UPDATE movie_type SET type_name=#{new_type_name} WHERE movie_id=#{movie_id} AND type_name=#{old_type_name}")
    public int updateMoviesTypes1(String new_type_name,Integer movie_id,String old_type_name);//用于电影信息中类型的更新


    @Delete("DELETE FROM movie_type WHERE type_name=#{type_name}")
    public int deleteMoviesTypes(String type_name);//用于类型管理中的删除

    @Delete("DELETE FROM movie_type WHERE movie_id=#{movie_id}")
    public int deleteMoviesTypes1(Integer movie__id);//用于删除电影信息时类型的删除
}
