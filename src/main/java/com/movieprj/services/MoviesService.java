package com.movieprj.services;

import com.movieprj.beans.Comment;
import com.movieprj.beans.Movies;

import java.util.List;

public interface MoviesService {
    public List<Movies> findAll();

    public  List<Movies> selectMoviesByOnshow(boolean onshow);

    public void addMovies(Movies movies);

    public void deleteMovies(Integer id);

    public void updateMovies(Movies movies);

}
