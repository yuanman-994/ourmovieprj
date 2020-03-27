package com.movieprj.services;

import com.movieprj.beans.Movies;
import com.movieprj.mapper.MoviesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MoviesServiceImp implements MoviesService {
    @Resource
    private MoviesMapper moviesMapper;

    @Override
    public List<Movies> findAll() {
        return moviesMapper.findAll();
    }

    @Override
    public Movies selectMoviesById(Integer movie_id) {
        Movies movie = moviesMapper.selectMoviesById(movie_id);
        return movie;
    }

    @Override
    public  List<Movies> selectMoviesByOnshow(boolean onshow){
        return moviesMapper.selectMoviesByOnshow(onshow);
    }

    @Override
    public void addMovies(Movies movies) {
        moviesMapper.addMovies(movies);
    }

    @Override
    public void deleteMovies(Integer id) {
        moviesMapper.deleteMovies(id);
    }

    @Override
    public void updateMovies(Movies movies) {
        moviesMapper.updateMovies(movies);
    }


}
