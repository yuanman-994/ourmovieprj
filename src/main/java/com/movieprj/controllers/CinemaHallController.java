package com.movieprj.controllers;

import com.movieprj.beans.Cinema;
import com.movieprj.beans.Hall;
import com.movieprj.services.CinemaServiceImp;
import com.movieprj.services.HallServiceImp;
import com.movieprj.services.SeatServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CinemaHallController {
    @Autowired
    private HallServiceImp hallService;

    @Autowired
    private SeatServiceImp seatService;

    @Autowired
    private CinemaServiceImp cinemaService;

    @GetMapping("/getCinema")
    public List<Cinema> getCinema() {
        return cinemaService.findAllCinema();
    }

    @GetMapping("/findCinema")
    public Cinema findCinema(@RequestBody Integer cinema_id) {
        return cinemaService.findCinemaById(cinema_id);
    }

    @PostMapping("/addCinema")
    public int addCinema(@RequestBody Map<String, String> cinemaMap) {
        return cinemaService.addCinema(cinemaMap);
    }


    @PostMapping("/deleteCinema")
    public int deleteCinema(@RequestBody List<Integer> cinema_ids) {
        return cinemaService.deleteCinema(cinema_ids);
    }

    @PostMapping("/updateCinema")
    public int updateCinema(@RequestBody Map<String, String> cinemaMap) {
        return cinemaService.updateCinema(cinemaMap);
    }


    @GetMapping("/getAllHall")
    public List<Hall> getAllHall() {
        return hallService.findAllHall();
    }

    @GetMapping("/findHall")
    public Hall findHall(@RequestBody Integer hall_id) {
        return hallService.findHallById(hall_id);
    }

    @GetMapping("/findHallByCinema")
    public List<Hall> findHallByCinema(@RequestBody Integer cinema_id) {
        return hallService.findHallByCinemaId(cinema_id);
    }

    @PostMapping("/addHall")
    public int addHall(@RequestBody Map<String, String> hallMap) {
        return hallService.addHall(hallMap);
    }

    @PostMapping("/deleteHall")
    public int deleteHall(@RequestBody List<Integer> hall_ids) {
        return hallService.deleteHall(hall_ids);
    }

    @PostMapping("/updateHall")
    public int updateHall(@RequestBody Map<String, String> hallMap) {
        return hallService.updateHall(hallMap);
    }

}
