package com.movieprj.services;


import com.movieprj.beans.Cinema;
import com.movieprj.mapper.CinemaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CinemaServiceImp implements CinemaService{
    @Resource
    private CinemaMapper cinemaMapper;

    @Override
    public Cinema findCinemaById(Integer cinema_id) {
        return cinemaMapper.findCinemaById(cinema_id);
    }

    @Override
    public int addCinema(Cinema cinema) {
        cinemaMapper.addCinema(cinema);
        return 1;
    }

    @Override
    public int deleteCinema(Integer cinema_id) {
        cinemaMapper.deleteCinema(cinema_id);
        return 1;
    }

    @Override
    public int updateCinema(Cinema cinema) {
        cinemaMapper.updateCinema(cinema);
        return 1;
    }
}
