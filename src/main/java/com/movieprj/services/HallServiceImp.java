package com.movieprj.services;

import com.movieprj.beans.Hall;
import com.movieprj.mapper.HallMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HallServiceImp implements HallService{

    @Resource
    private HallMapper hallMapper;

    @Override
    public Hall findHallById(Integer hall_id) {
        return hallMapper.findHallById(hall_id);
    }
}
