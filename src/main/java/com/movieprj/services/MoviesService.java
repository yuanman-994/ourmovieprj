package com.movieprj.services;

import com.movieprj.beans.Comment;
import com.movieprj.beans.Movies;

import java.util.List;

public interface MoviesService {
    public List<Movies> findAll();

    public Movies selectMoviesById(Integer movie_id);

    public  List<Movies> selectMoviesByOnshow(Integer onshow);

    public int addMovies(Movies movies);

    public int deleteMovies(Integer movie_id);

    public int updateMovies(Movies movies);

}
