package com.movieprj.controllers;

import com.movieprj.beans.Cinema;
import com.movieprj.beans.Comment;
import com.movieprj.beans.MovieSchedule;
import com.movieprj.beans.Movies;
import com.movieprj.mapper.ScheduleMapper;
import com.movieprj.services.CommentServiceImp;
import com.movieprj.services.MoviesServiceImp;
import com.movieprj.services.ScheduleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping
public class MoviesController {
    @Autowired
    private MoviesServiceImp moviesService;

    @Autowired
    private CommentServiceImp commentService;

    @Autowired
    private ScheduleServiceImp scheduleService;

    @RequestMapping("/movie_single")
    public  String toMoviePage(Integer movie_id ){
        return "redirect:/movie_single1?movie_id="+movie_id;
     }

    @RequestMapping("/movie_single1")
    public  String toMoviePage1(Integer movie_id){
        return "movie_single";
    }

    @RequestMapping("/movie_schedule")
    public  String toMovieSchedule(Integer movie_id ){
        return "redirect:/movie_schedule1?movie_id="+movie_id;
    }

    @RequestMapping("/movie_schedule1")
    public  String toMovieSchedule1(Integer movie_id ){
        return "movie_schedule";
    }

    @RequestMapping("/schedule_cinema")
    @ResponseBody
    public Map<String,Object>findCinemaByMovieIdAndDate(@RequestBody Map<String,Object> params){
        Integer movie_id = Integer.parseInt(params.get("movie_id").toString());
        int day = Integer.parseInt(params.get("day").toString());
        String date_time;
        Date date=new Date();
        Date date1,date2;
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.add(Calendar.DAY_OF_MONTH,1);//利用Calendar 实现 Date日期+1天  
        date1 = c.getTime();
        c.add(Calendar.DAY_OF_MONTH,1);//利用Calendar 实现 Date日期+2天  
        date2 = c.getTime();
        if(day==0){//当前时间
            date_time=simpleDate.format(date);
        }else if (day==1){//明天
            date_time=simpleDate.format(date1);
        } else{//后天
            date_time=simpleDate.format(date2);
        }
        List<Cinema> cinemaList = scheduleService.findCinemaByMovieIdAndDate(movie_id,date_time);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cinemaList",cinemaList);
        return map;
    }


    @RequestMapping("/schedule_detail")
    @ResponseBody
    public Map<String,Object> findScheduleByMovieIdAndCinemaIdAndDate(@RequestBody Map<String,Object> params){
        Integer movie_id = Integer.parseInt(params.get("movie_id").toString());
        Integer cinema_id = Integer.parseInt(params.get("cinema_id").toString());
        int day = Integer.parseInt(params.get("day").toString());
        String date_time;
        Date date=new Date();
        Date date1,date2;
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.add(Calendar.DAY_OF_MONTH,1);//利用Calendar 实现 Date日期+1天  
        date1 = c.getTime();
        c.add(Calendar.DAY_OF_MONTH,1);//利用Calendar 实现 Date日期+2天  
        date2 = c.getTime();
        if(day==0){//当前时间
            date_time=simpleDate.format(date);
        }else if (day==1){//明天
            date_time=simpleDate.format(date1);
        } else{//后天
            date_time=simpleDate.format(date2);
        }
        List<MovieSchedule> scheduleList = scheduleService.findScheduleByMovieIdAndCinemaIdAndDate(cinema_id,movie_id,date_time);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("scheduleList",scheduleList);
        return map;
    }

    /*@GetMapping("/movie_single2")
    public String toMoviePage(Integer movie_id, Model model, HttpSession httpSession){
        //boolean isLogin = httpSession.getAttribute()!=null;
        List<Comment> commentList = commentService.findCommentWithUserByMovieId(movie_id);
        Movies movie = moviesService.selectMoviesById(movie_id);
        model.addAttribute("commentList",commentList);
        model.addAttribute("movie",movie);
        return "movie_single";
    }*/

    @RequestMapping("/moviesInfo")
    @ResponseBody
    public  Map<String,Object> ShowMoivesInfo(Integer movie_id){
        Movies movie = moviesService.selectMoviesById(movie_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("movie",movie);
        return map;
    }


    @RequestMapping("/moviesComment")
    @ResponseBody
    public  Map<String,Object> ShowMoivesComments(Integer movie_id){
        List<Comment> commentList = commentService.findCommentWithUserByMovieId(movie_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("commentList",commentList);
        return map;
    }
    @RequestMapping("/onshowMovies")
    @ResponseBody
    public Map<String,Object> ShowOnshowMoives(Integer onshow){
        List<Movies> moviesList = moviesService.selectMoviesByOnshow(onshow);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("moviesList",moviesList);
        return map;
    }



    @PostMapping("/addMComment")
    public String AddMComment(HttpServletRequest request, Model model) {
        Comment comment= new Comment();
        comment.setContent(request.getParameter("content"));
        if(request.getParameter("movie_id")!=null){
        Integer movie_id = Integer.valueOf(request.getParameter("movie_id"));
        comment.setMovie_id(movie_id);}
        if(request.getParameter("user_id")!=null){
        Integer user_id = Integer.valueOf(request.getParameter("user_id"));
        comment.setUser_id(user_id);}
        Date date=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comment.setComment_time(simpleDate.format(date));
        commentService.insertComment(comment);
        return "redirect:/movie_single1?movie_id="+comment.getMovie_id();
    }

}
