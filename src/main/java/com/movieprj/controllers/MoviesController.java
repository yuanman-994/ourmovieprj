package com.movieprj.controllers;

import com.movieprj.beans.Comment;
import com.movieprj.beans.Movies;
import com.movieprj.services.CommentServiceImp;
import com.movieprj.services.MoviesServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MoviesController {
    @Autowired
    private MoviesServiceImp moviesService;

    @Autowired
    private CommentServiceImp commentService;

    @RequestMapping("/moviesinfo")
    public @ResponseBody List<Movies> ShowMoivesInfo(){
         List<Movies> list = moviesService.findAll();
         return list;
     //return list.stream().map(Movies::getName).collect(Collectors.toList());
    }
    @RequestMapping("/onshowmoviesinfo")
    public List<Movies> ShowOnshowMoives(boolean onshow){
        List<Movies> list = moviesService.selectMoviesByOnshow(onshow);
        return list;
    }

    @RequestMapping("/comment")
    @ResponseBody
    public List<Comment> ShowMoviesComment(Integer movieId){
        List<Comment> list = commentService.findCommentByMovieId(movieId);
        return list;
    }

    @RequestMapping("/test")
    @ResponseBody
    public Map<String,Object> test() {
        Movies movies = new Movies();
        Movies movies1 = new Movies();
        List<Movies> list = new ArrayList<Movies>();
        list.add(movies);
        list.add(movies1);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list",list);
        return map;
    }
}
