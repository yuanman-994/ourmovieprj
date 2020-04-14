package com.movieprj.services;

import com.movieprj.beans.Cinema;

public interface CinemaService {

    public Cinema findCinemaById(Integer cinema_id);
    public int addCinema(Cinema cinema);
    public int deleteCinema(Integer cinema_id);
    public int updateCinema(Cinema cinema);
}
