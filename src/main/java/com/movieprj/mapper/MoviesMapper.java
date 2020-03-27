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

    public  List<Movies> selectMoviesByOnshow(boolean onshow);

    public void addMovies(Movies movies);

    public void deleteMovies(Integer id);

    public void updateMovies(Movies movies);

}
