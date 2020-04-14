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
    public  List<Movies> selectMoviesByOnshow(Integer onshow){
        return moviesMapper.selectMoviesByOnshow(onshow);
    }

    @Override
    public int addMovies(Movies movies) {
        moviesMapper.addMovies(movies);
        return 1;
    }

    @Override
    public int deleteMovies(Integer movie_id) {
        moviesMapper.deleteMovies(movie_id);
        return 1;
    }

    @Override
    public int updateMovies(Movies movies) {
        moviesMapper.updateMovies(movies);
        return 1;
    }


}
