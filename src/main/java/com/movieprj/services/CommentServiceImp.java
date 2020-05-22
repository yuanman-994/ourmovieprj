package com.movieprj.services;

import com.movieprj.beans.Comment;
import com.movieprj.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    public Comment findCommentWithUserByPk(Integer movie_id,Integer user_id){
        return commentMapper.findCommentWithUserByPk(movie_id,user_id);
    }

    @Override
    public List<Comment> findAllCommentWithUser(){
        List<Comment> commentList = commentMapper.findAllCommentWithUser();
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
    public int deleteComment(Map<String,List> mu_ids) {
        List mids = mu_ids.get("movie_ids");
        List uids = mu_ids.get("user_ids");
        for(int i=0 ;i<mids.size();i++) {
            commentMapper.deleteComment(Integer.valueOf(mids.get(i).toString()),Integer.valueOf(uids.get(i).toString()));
        }
        return 1;
    }

    @Override
    public List<Comment> searchComment(String movie_name, String user_name) {
        List<Comment> commentList = commentMapper.searchComment(movie_name,user_name);
        return commentList;
    }

}
