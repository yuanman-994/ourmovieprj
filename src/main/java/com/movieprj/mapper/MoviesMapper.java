package com.movieprj.mapper;

import com.movieprj.beans.Movies;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;

//@Repository
@Mapper
public interface MoviesMapper {
    public List<Movies> findAll();

    @Select("SELECT * FROM movie WHERE movie_id =#{movie_id}")
    public Movies selectMoviesById(Integer movie_id);

    @Select("SELECT * FROM movie WHERE onshow =1")
    public  List<Movies> selectMoviesByOnshow(Integer onshow);

    public void addMovies(Movies movies);

    public void deleteMovies(Integer id);

    public void updateMovies(Movies movies);

}
