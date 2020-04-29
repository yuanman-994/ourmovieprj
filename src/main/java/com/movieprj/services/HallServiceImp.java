package com.movieprj.services;

import com.movieprj.beans.Cinema;
import com.movieprj.beans.Hall;
import com.movieprj.beans.Seat;
import com.movieprj.mapper.CinemaMapper;
import com.movieprj.mapper.HallMapper;
import com.movieprj.mapper.SeatMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HallServiceImp implements HallService{

    @Resource
    private CinemaMapper cinemaMapper;

    @Resource
    private HallMapper hallMapper;

    @Resource
    private SeatMapper seatMapper;

    @Override
    public List<Hall> findAllHall() {
        return hallMapper.findAllHall();
    }

    @Override
    public Hall findHallById(Integer hall_id) {
        return hallMapper.findHallById(hall_id);
    }

    @Override
    public List<Hall> findHallByCinemaId(Integer cinema_id) {
        return hallMapper.findHallByCinemaId(cinema_id);
    }

    @Override
    public int addHall(Map<String,Object> hallMap) {
        try {
            Hall hall = new Hall();
            hall = (Hall)hallMap.get("hallMap");
            Map<String, Object> map = new HashMap<String, Object>();
            List<Seat> seatList = new ArrayList<Seat>();
            Integer hall_id=hall.getHall_id();
            for(int i=1;i<=100;i++){
                Seat seat = new Seat();
                seat.setHall_id(hall_id);
                if(i%10==0){
                seat.setLoc_x((i/10));
                seat.setLoc_y(10);
                seat.setSeat_id(hall_id*1000+i);
                seatList.add(seat);
                }
                else {
                    seat.setLoc_x((i/10)+1);
                    seat.setLoc_y(i%10);
                    seat.setSeat_id(hall_id*1000+i);
                    seatList.add(seat);
                }
            }
            seatMapper.batchAddSeats(seatList);//添加影厅的同时添加对应的座位信息（100个），可用座位由hall的seatmap属性来管理
            hallMapper.addHall(hall);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public int deleteHall(Map<String,List> hall_ids) {
        List ids = hall_ids.get("hall_ids");
        for(int i=0 ;i<ids.size();i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            List<Seat> seatList = new ArrayList<Seat>();
            Integer hall_id=Integer.valueOf(ids.get(i).toString());
            for(int n=1;n<=100;n++){
                Seat seat = new Seat();
                seat.setHall_id(hall_id);
                if(n%10==0){
                    seat.setLoc_x((n/10));
                    seat.setLoc_y(10);
                    seat.setSeat_id(hall_id*1000+n);
                    seatList.add(seat);
                }
                else {
                    seat.setLoc_x((n/10)+1);
                    seat.setLoc_y(n%10);
                    seat.setSeat_id(hall_id*1000+n);
                    seatList.add(seat);
                }
            }
            seatMapper.batchDeleteSeats(seatList);//删除影厅的同时删除对应的座位信息（100个）
            hallMapper.deleteHall(Integer.valueOf(ids.get(i).toString()));
        }
        return 1;
    }

    @Override
    public int updateHall(Map<String,Object> hallMap) {
        try {
            Hall hall = new Hall();
            hall = (Hall)hallMap.get("hallMap");
            hallMapper.updateHall(hall);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public Map<String,Object> searchHallByMovies(String search_content) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<Cinema> cinemaList = cinemaMapper.findCinemaByName(search_content);
        for(int i = 0;i<cinemaList.size();i++){
            Integer cinema_id =  cinemaList.get(i).getCinema_id();
            List<Hall> hallList = hallMapper.findHallByCinemaId(cinema_id);
            map.put("hallList"+i,hallList);
        }
        return map;
    }
}
