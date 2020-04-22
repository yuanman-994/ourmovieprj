package com.movieprj.services;

import com.movieprj.beans.Comment;
import com.movieprj.beans.Movies;

import java.util.List;
import java.util.Map;

public interface MoviesService {
    public List<Movies> findAll();

    public Movies selectMoviesById(Integer movie_id);

    public Movies selectMoviesWithTypesById(Integer movie_id);

    public List<Movies> findAllWithTypes();

    public  List<Movies> selectMoviesByOnshow(Integer onshow);

    public int addMovies(Map<String,Object> moviesMap);

    public int deleteMovies(Map<String,List> movies_ids);

    public int updateMovies(Map<String,Object> moviesMap);

}
