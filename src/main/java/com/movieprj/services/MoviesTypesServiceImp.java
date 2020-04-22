package com.movieprj.services;

import com.movieprj.beans.MoviesTypes;
import com.movieprj.mapper.MoviesTypesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
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
    public int updateMoviesTypes1(MoviesTypes moviesTypes) {
        moviesTypesMapper.updateMoviesTypes1(moviesTypes);
        return 1;
    }

    @Override
    public int deleteMoviesTypes(Map<String,List> types_names) {
        List names = types_names.get("types_names");
        for(int i=0 ;i<names.size();i++) {
            moviesTypesMapper.deleteMoviesTypes(names.get(i).toString());
        }
        return 1;
    }

    @Override
    public int deleteMoviesTypes1(Integer movie_id) {
        moviesTypesMapper.deleteMoviesTypes1(movie_id);
        return 1;
    }
}
