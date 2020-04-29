package com.movieprj.services;


import com.movieprj.beans.Cinema;
import com.movieprj.beans.Hall;
import com.movieprj.mapper.CinemaMapper;
import com.movieprj.mapper.HallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CinemaServiceImp implements CinemaService{
    @Resource
    private CinemaMapper cinemaMapper;

    @Resource
    private HallMapper hallMapper;

    @Autowired
    private HallServiceImp hallServiceImp;


    @Override
    public List<Cinema> findAllCinema(){return  cinemaMapper.findAllCinema();}

    @Override
    public Cinema findCinemaById(Integer cinema_id) {
        return cinemaMapper.findCinemaById(cinema_id);
    }

    @Override
    public int addCinema(Map<String,Object> cinemaMap) {
        try {
            Cinema cinema = new Cinema();
            cinema = (Cinema)cinemaMap.get("cinemaMap");
            cinemaMapper.addCinema(cinema);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public int deleteCinema(Map<String,List> cinema_ids) {
        List ids = cinema_ids.get("cinema_ids");
        for(int i=0 ;i<ids.size();i++) {
            Map<String, List> map = new HashMap<String, List>();
            List<Hall> hallList = new ArrayList<Hall>();
            Integer cinema_id=Integer.valueOf(ids.get(i).toString());
            hallList = hallMapper.findHallByCinemaId(cinema_id);
            List hall_ids = new ArrayList();
            for(int n=0;n<hallList.size();n++){
            Integer id = hallList.get(n).getHall_id();
            hall_ids.add(id);
            }
            map.put("hall_ids",hall_ids);
            hallServiceImp.deleteHall(map);
            cinemaMapper.deleteCinema(Integer.valueOf(ids.get(i).toString()));
        }
        return 1;
    }

    @Override
    public int updateCinema(Map<String,Object> cinemaMap) {
        try {
            Cinema cinema = new Cinema();
            cinema = (Cinema)cinemaMap.get("cinemaMap");
            cinemaMapper.updateCinema(cinema);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public List<Cinema> findCinemaByName(String movie_name){
        return cinemaMapper.findCinemaByName(movie_name);
    }
}
