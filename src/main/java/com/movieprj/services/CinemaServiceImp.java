package com.movieprj.services;


import com.movieprj.beans.Cinema;
import com.movieprj.mapper.CinemaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CinemaServiceImp implements CinemaService{
    @Resource
    private CinemaMapper cinemaMapper;

    @Override
    public List<Cinema> findAllCinema(){return  cinemaMapper.findAllCinema();}

    @Override
    public Cinema findCinemaById(Integer cinema_id) {
        return cinemaMapper.findCinemaById(cinema_id);
    }

    @Override
    public int addCinema(Map<String,String> cinemaMap) {
        try {
            Cinema cinema = new Cinema();
            Integer cinema_id;
            String cinema_name, cinema_address, cinema_introduction;
            cinema_id = Integer.valueOf(cinemaMap.get("cinema_id"));
            cinema_name = cinemaMap.get("cinema_name");
            cinema_address = cinemaMap.get("cinema_address");
            cinema_introduction = cinemaMap.get("cinema_introduction");
            cinema.setCinema_id(cinema_id);
            cinema.setCinema_name(cinema_name);
            cinema.setCinema_address(cinema_address);
            cinema.setCinema_introduction(cinema_introduction);
            cinemaMapper.addCinema(cinema);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public int deleteCinema(List<Integer> cinema_ids) {
        for(int i=0 ;i<cinema_ids.size();i++) {
            cinemaMapper.deleteCinema(cinema_ids.get(i));
        }
        return 1;
    }

    @Override
    public int updateCinema(Map<String,String> cinemaMap) {
        try {
            Cinema cinema = new Cinema();
            Integer cinema_id;
            String cinema_name, cinema_address, cinema_introduction;
            cinema_id = Integer.valueOf(cinemaMap.get("cinema_id"));
            cinema_name = cinemaMap.get("cinema_name");
            cinema_address = cinemaMap.get("cinema_address");
            cinema_introduction = cinemaMap.get("cinema_introduction");
            cinema.setCinema_id(cinema_id);
            cinema.setCinema_name(cinema_name);
            cinema.setCinema_address(cinema_address);
            cinema.setCinema_introduction(cinema_introduction);
            cinemaMapper.updateCinema(cinema);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
}
