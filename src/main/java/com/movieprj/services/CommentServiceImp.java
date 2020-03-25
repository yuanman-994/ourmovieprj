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
    public int insertComment(Comment comment) {
        commentMapper.insertComment(comment);
        //comment.setUser(userMapper.getUserById(comment.getUser_id()));//设置comment对象的User属性
        return 0;
    }

    @Override
    public int updateComment(Comment comment) {
        commentMapper.updateComment(comment);
        return 0;
    }

    @Override
    public int deleteComment(Integer id) {
        commentMapper.deleteComment(id);
        return 0;
    }

}
