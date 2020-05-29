package com.movieprj.services;

import com.movieprj.beans.Movies;
import com.movieprj.mapper.MoviesMapper;
import com.movieprj.mapper.MoviesTypesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MoviesServiceImp implements MoviesService {
    @Resource
    private MoviesMapper moviesMapper;

    @Resource
    private MoviesTypesMapper moviesTypesMapper;

    @Override
    public List<Movies> findAll() {
        return moviesMapper.findAll();
    }

    @Override
    public Movies selectMoviesById(Integer movie_id) {
        Movies movie = moviesMapper.selectMoviesById(movie_id);
        return movie;
    }

    public Movies selectMoviesWithTypesById(Integer movie_id){
        return moviesMapper.selectMoviesWithTypesById(movie_id);
    }

    @Override
    public List<Movies> findAllWithTypes(){
        return  moviesMapper.findAllWithTypes();
    }

    @Override
    public  List<Movies> selectMoviesByOnshow(Integer onshow){
        return moviesMapper.selectMoviesByOnshow(onshow);
    }

    @Override
    public int addMovies(Map<String,Object> moviesMap) {
        try {
            Movies movies = new Movies();
            movies = (Movies) moviesMap.get("moviesMap");
            moviesMapper.addMovies(movies);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public int deleteMovies(Map<String,List> movies_ids) {
        List ids = movies_ids.get("movies_ids");
        for(int i=0 ;i<ids.size();i++) {
            moviesMapper.deleteMovies(Integer.valueOf(ids.get(i).toString()));
            moviesTypesMapper.deleteMoviesTypes1(Integer.valueOf(ids.get(i).toString()));
        }
        return 1;
    }

    @Override
    public int updateMovies(Map<String,Object> moviesMap) {
        try {
            Movies movies = new Movies();
            movies = (Movies) moviesMap.get("moviesMap");
            moviesMapper.updateMovies(movies);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Map<String, Object> searchMovies(String movie_name,String select_date,String select_status) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<Movies> moviesList = moviesMapper.searchMovies(movie_name,select_date,select_status);
        map.put("moviesList",moviesList);
        return map;
    }

    @Override
    public List<Movies> selectMoviesByRank() {
        return moviesMapper.selectMoviesByRank();
    }

    @Override
    public List<Movies> orderMoviesByRank() {
        return moviesMapper.orderMoviesByRank();
    }


}
