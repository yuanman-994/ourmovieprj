package com.movieprj.controllers;

import com.movieprj.beans.Cinema;
import com.movieprj.beans.Hall;
import com.movieprj.services.CinemaServiceImp;
import com.movieprj.services.HallServiceImp;
import com.movieprj.services.SeatServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class CinemaHallController {
    @Autowired
    private HallServiceImp hallService;

    @Autowired
    private SeatServiceImp seatService;

    @Autowired
    private CinemaServiceImp cinemaService;

    @RequestMapping("/admin_cinema")
    public String cinema_index(){ return "admin_cinema";}

    @RequestMapping("/admin_hall")
    public String hall_index(){ return "admin_hall";}

    @RequestMapping("/getCinema")
    @ResponseBody
    public Map<String,Object> getCinema() {
        List<Cinema> cinemaList = cinemaService.findAllCinema();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cinemaList",cinemaList);
        return map;
    }

    @RequestMapping("/findCinema")
    @ResponseBody
    public Map<String,Object> findCinema(Integer cinema_id) {
        Cinema cinema = cinemaService.findCinemaById(cinema_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cinema",cinema);
        return map;

    }

    @PostMapping("/addCinema")
    public String addCinema(HttpServletRequest request) {
        Cinema cinema = new Cinema();
        if(request.getParameter("cinema_id")!=null){
            Integer cinema_id = Integer.valueOf(request.getParameter("cinema_id"));
            cinema.setCinema_id(cinema_id);}
        if(request.getParameter("cinema_name")!=null){
            String  cinema_name = request.getParameter("cinema_name");
            cinema.setCinema_name(cinema_name);}
        if(request.getParameter("cinema_address")!=null){
            String  cinema_address = request.getParameter("cinema_address");
            cinema.setCinema_address(cinema_address);}
        if(request.getParameter("cinema_introduction")!=null){
            String  cinema_introduction = request.getParameter("cinema_introduction");
            cinema.setCinema_introduction(cinema_introduction);}
        Map<String, Object> cinemaMap = new HashMap<String,Object>();
        cinemaMap.put("cinemaMap",cinema);
        cinemaService.addCinema(cinemaMap);
        return "redirect:/admin_cinema";
    }


    @PostMapping("/deleteCinema")
    @ResponseBody
    public Map<String,Integer> deleteCinema(@RequestBody Map<String,List> cinema_ids) {
        int check;
        check = cinemaService.deleteCinema(cinema_ids);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("check",check);
        return map;
    }

    @PostMapping("/updateCinema")
    public String updateCinema(HttpServletRequest request) {
        Cinema cinema = new Cinema();
        if(request.getParameter("cinema_id")!=null){
            Integer cinema_id = Integer.valueOf(request.getParameter("cinema_id"));
            cinema.setCinema_id(cinema_id);}
        if(request.getParameter("cinema_name")!=null){
            String  cinema_name = request.getParameter("cinema_name");
            cinema.setCinema_name(cinema_name);}
        if(request.getParameter("cinema_address")!=null){
            String  cinema_address = request.getParameter("cinema_address");
            cinema.setCinema_address(cinema_address);}
        if(request.getParameter("cinema_introduction")!=null){
            String  cinema_introduction = request.getParameter("cinema_introduction");
            cinema.setCinema_introduction(cinema_introduction);}
        Map<String, Object> cinemaMap = new HashMap<String,Object>();
        cinemaMap.put("cinemaMap",cinema);
        cinemaService.updateCinema(cinemaMap);
        return "redirect:/admin_cinema";
    }


    @RequestMapping("/getAllHall")
    @ResponseBody
    public Map<String,Object> getAllHall() {
        List<Hall> hallList = hallService.findAllHall();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hallList",hallList);
        return map;
    }

    @RequestMapping("/findHall")
    @ResponseBody
    public Map<String,Object> findHall(Integer hall_id) {
        Hall hall = hallService.findHallById(hall_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hall",hall);
        return map;

    }

    @RequestMapping("/findHallByCinema")
    @ResponseBody
    public List<Hall> findHallByCinema(@RequestBody Integer cinema_id) {
        return hallService.findHallByCinemaId(cinema_id);
    }

    @PostMapping("/addHall")
    public String addHall(HttpServletRequest request) {
        Hall hall = new Hall();
        if(request.getParameter("hall_id")!=null){
            Integer hall_id = Integer.valueOf(request.getParameter("hall_id"));
            hall.setHall_id(hall_id);}
        if(request.getParameter("cinema_id")!=null){
           Integer  cinema_id = Integer.valueOf(request.getParameter("cinema_id"));
            hall.setCinema_id(cinema_id);}
        if(request.getParameter("hall_name")!=null){
            String  hall_name = request.getParameter("hall_name");
            hall.setHall_name(hall_name);}
        if(request.getParameter("hall_number_of_seats")!=null){
            Integer  hall_number_of_seats = Integer.valueOf(request.getParameter("hall_number_of_seats"));
            hall.setNumber_of_seats(hall_number_of_seats);}
        if(request.getParameter("hall_seat_map")!=null){
            String  hall_seat_map = request.getParameter("hall_seat_map");
            hall.setSeat_map(hall_seat_map);}
        Map<String, Object> hallMap = new HashMap<String,Object>();
        hallMap.put("hallMap",hall);
        hallService.addHall(hallMap);
        return "redirect:/admin_hall";
    }

    @RequestMapping("/deleteHall")
    @ResponseBody
    public Map<String,Object> deleteHall(@RequestBody Map<String,List> hall_ids) {
        int check;
        check = hallService.deleteHall(hall_ids);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("check",check);
        return map;

    }

    @PostMapping("/updateHall")
    public String updateHall(HttpServletRequest request) {
        Hall hall = new Hall();
        if(request.getParameter("hall_id")!=null){
            Integer hall_id = Integer.valueOf(request.getParameter("hall_id"));
            hall.setHall_id(hall_id);}
        if(request.getParameter("cinema_id")!=null){
            Integer  cinema_id = Integer.valueOf(request.getParameter("cinema_id"));
            hall.setCinema_id(cinema_id);}
        if(request.getParameter("hall_name")!=null){
            String  hall_name = request.getParameter("hall_name");
            hall.setHall_name(hall_name);}
        if(request.getParameter("hall_number_of_seats")!=null){
            Integer  hall_number_of_seats = Integer.valueOf(request.getParameter("hall_number_of_seats"));
            hall.setNumber_of_seats(hall_number_of_seats);}
        if(request.getParameter("hall_seat_map")!=null){
            String  hall_seat_map = request.getParameter("hall_seat_map");
            hall.setSeat_map(hall_seat_map);}
        Map<String, Object> hallMap = new HashMap<String,Object>();
        hallMap.put("hallMap",hall);
        hallService.updateHall(hallMap);
        return "redirect:/admin_hall";
    }
}
