package com.movieprj.services;

import com.movieprj.beans.MoviesTypes;
import com.movieprj.mapper.MoviesTypesMapper;

import javax.annotation.Resource;
import java.util.List;

public class MoviesTypesServiceImp implements MoviesTypesService {
    @Resource
    private MoviesTypesMapper moviesTypesMapper;

    @Override
    public List<MoviesTypes> findTypesByMovieId(Integer movie_id) {
        return moviesTypesMapper.findTypesByMovieId(movie_id);
    }

    @Override
    public List<String> findTypesName() {
        return moviesTypesMapper.findTypesName();
    }

    @Override
    public int addMoviesType(MoviesTypes moviesTypes) {
        moviesTypesMapper.addMoviesType(moviesTypes);
        return 1;
    }

    @Override
    public int addMoviesType1(MoviesTypes moviesTypes) {
        moviesTypesMapper.addMoviesType1(moviesTypes);
        return 1;
    }

    @Override
    public int updateMoviesTypes(String new_type_name, String old_type_name) {
        moviesTypesMapper.updateMoviesTypes(new_type_name,old_type_name);
        return 1;
    }

    @Override
    public int updateMoviesTypes1(String new_type_name, Integer movie_id, String old_type_name) {
        moviesTypesMapper.updateMoviesTypes1(new_type_name, movie_id, old_type_name);
        return 1;
    }

    @Override
    public int deleteMoviesTypes(String type_name) {
        moviesTypesMapper.deleteMoviesTypes(type_name);
        return 1;
    }

    @Override
    public int deleteMoviesTypes1(Integer movie_id) {
        moviesTypesMapper.deleteMoviesTypes1(movie_id);
        return 1;
    }
}
