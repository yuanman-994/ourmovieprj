package com.movieprj.controllers;

import com.movieprj.beans.Comment;
import com.movieprj.beans.Movies;
import com.movieprj.services.CommentServiceImp;
import com.movieprj.services.MoviesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping
public class MoviesController {
    @Autowired
    private MoviesServiceImp moviesService;

    @Autowired
    private CommentServiceImp commentService;

    @RequestMapping("/movie_single")
    public  String toMoviePage(Integer movie_id ){
        return "redirect:/movie_single1?movie_id="+movie_id;
     }

    @RequestMapping("/movie_single1")
    public  String toMoviePage1(Integer movie_id){
        return "movie_single";
    }


    @GetMapping("/movie_single2")
    public String toMoviePage(Integer movie_id, Model model, HttpSession httpSession){
        //boolean isLogin = httpSession.getAttribute()!=null;
        List<Comment> commentList = commentService.findCommentWithUserByMovieId(movie_id);
        Movies movie = moviesService.selectMoviesById(movie_id);
        model.addAttribute("commentList",commentList);
        model.addAttribute("movie",movie);
        return "movie_single";
    }

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
    @RequestMapping("/onshowmoviesinfo")
    public List<Movies> ShowOnshowMoives(boolean onshow){
        List<Movies> list = moviesService.selectMoviesByOnshow(onshow);
        return list;
    }
    @RequestMapping("/getComment")
    public String moviesTest() {
        return "movies_test";
    }

}
