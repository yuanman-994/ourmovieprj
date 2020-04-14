package com.movieprj.services;

import com.movieprj.beans.Comment;
import com.movieprj.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService{

    @Resource
    private CommentMapper commentMapper;
    //@Resource
    //private  UserMapper userMapper;
    @Override
    public List<Comment> findCommentByMovieId(Integer movieId) {
        List<Comment> commentList = commentMapper.findCommentByMovieId(movieId);
        return commentList;
    }

    @Override
    public List<Comment> findCommentWithUserByMovieId(Integer id) {
        List<Comment> commentList = commentMapper.findCommentWithUserByMovieId(id);
        return commentList;
    }

    @Override
    public int insertComment(Comment comment) {
        commentMapper.insertComment(comment);
        return 0;
    }

    @Override
    public int updateComment(Comment comment) {
        commentMapper.updateComment(comment);
        return 0;
    }

    @Override
    public int deleteComment(Integer movie_id,Integer user_id) {
        commentMapper.deleteComment(movie_id,user_id);
        return 0;
    }

}
