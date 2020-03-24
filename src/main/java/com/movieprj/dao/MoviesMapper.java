package com.movieprj.dao;

import com.movieprj.beans.Movies;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

//@Repository
@Mapper
public interface MoviesMapper {
    public List<Movies> findAll();

    public  List<Movies> selectMoviesByOnshow(boolean onshow);

    public void addMovies(Movies movies);

    public void deleteMovies(Integer id);

    public void updateMovies(Movies movies);

}
