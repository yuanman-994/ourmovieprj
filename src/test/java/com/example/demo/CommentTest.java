package com.example.demo;


import com.movieprj.OurmovieprjApplication;

import com.movieprj.beans.Comment;
import com.movieprj.mapper.CommentMapper;
import com.movieprj.services.CommentService;
import com.movieprj.services.CommentServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes= OurmovieprjApplication.class)
public class CommentTest {
    @Resource
    private CommentMapper commentMapper;

    @Test
    public void findCommentWithUserByMovieId(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Comment> commentList = commentMapper.findCommentWithUserByMovieId(1);
        map.put("commentList",commentList);
        System.out.println(map);

    }
}
