package com.movieprj.services;

import com.movieprj.beans.MoviesTypes;

import java.util.List;
import java.util.Map;

public interface MoviesTypesService {


    public List<MoviesTypes> findTypesByMovieId(Integer movie_id);


    public List<String> findTypesName();//用于显示已有全部的类型名单


    public int addMoviesType(MoviesTypes moviesTypes);//用于类型管理的类型添加


    public int addMoviesType1(MoviesTypes moviesTypes);//用于添加电影时的类型的设置


    public int updateMoviesTypes(String new_type_name,String old_type_name);//用于类型管理的更新(所有类型名都同时更新)


    public int updateMoviesTypes1(MoviesTypes moviesTypes);//用于电影信息中类型的更新



    public int deleteMoviesTypes(Map<String,List> types_names);//用于类型管理中的删除


    public int deleteMoviesTypes1(Integer movie__id);//用于删除电影信息时类型的删除
}
