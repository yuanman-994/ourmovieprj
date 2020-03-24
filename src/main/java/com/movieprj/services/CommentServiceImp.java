package com.movieprj.services;

import com.movieprj.beans.Comment;
import com.movieprj.dao.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService{

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> findCommentByMovieId(Integer movieId) {
        List<Comment> commentList = commentMapper.findCommentByMovieId(movieId);
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
    public int deleteComment(Integer id) {
        commentMapper.deleteComment(id);
        return 0;
    }

}
