package com.movieprj.services;

import com.movieprj.beans.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    public List<Comment> findCommentByMovieId(Integer movieId);

    public List<Comment> findCommentWithUserByMovieId(Integer id);

    public Comment findCommentWithUserByPk(Integer movie_id,Integer user_id);

    public List<Comment> findAllCommentWithUser();

    public int insertComment(Comment comment);

    public int updateComment(Comment comment);

    public int deleteComment(Map<String,List> mu_ids);

    public List<Comment> searchComment(String movie_name,String user_name);
}
