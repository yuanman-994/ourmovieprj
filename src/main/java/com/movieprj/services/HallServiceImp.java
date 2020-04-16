package com.movieprj.services;

import com.movieprj.beans.Hall;
import com.movieprj.mapper.HallMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HallServiceImp implements HallService{

    @Resource
    private HallMapper hallMapper;

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
    public int addHall(Map<String,String> hallMap) {
        try {
            Hall hall = new Hall();
            Integer hall_id,cinema_id;
            int number_of_seats;
            String hall_name,seat_map ;
            cinema_id = Integer.valueOf(hallMap.get("cinema_id"));
            hall_name = hallMap.get("cinema_name");
            hall_id = Integer.valueOf(hallMap.get("hall_id"));
            number_of_seats = Integer.valueOf(hallMap.get("number_of_seats"));
            seat_map = hallMap.get("seat_map");
            hall.setCinema_id(cinema_id);
            hall.setHall_name(hall_name);
            hall.setHall_id(hall_id);
            hall.setNumber_of_seats(number_of_seats);
            hall.setSeat_map(seat_map);
            hallMapper.addHall(hall);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public int deleteHall(List<Integer> hall_ids) {
        for(int i=0 ;i<hall_ids.size();i++) {
            hallMapper.deleteHall(hall_ids.get(i));
        }
        return 1;
    }

    @Override
    public int updateHall(Map<String,String> hallMap) {
        try {
            Hall hall = new Hall();
            Integer hall_id,cinema_id;
            int number_of_seats;
            String hall_name,seat_map ;
            cinema_id = Integer.valueOf(hallMap.get("cinema_id"));
            hall_name = hallMap.get("cinema_name");
            hall_id = Integer.valueOf(hallMap.get("hall_id"));
            number_of_seats = Integer.valueOf(hallMap.get("number_of_seats"));
            seat_map = hallMap.get("seat_map");
            hall.setCinema_id(cinema_id);
            hall.setHall_name(hall_name);
            hall.setHall_id(hall_id);
            hall.setNumber_of_seats(number_of_seats);
            hall.setSeat_map(seat_map);
            hallMapper.updateHall(hall);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
}
