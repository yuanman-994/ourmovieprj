package com.movieprj.services;

import com.movieprj.beans.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> findCommentByMovieId(Integer movieId);

    public List<Comment> findCommentWithUserByMovieId(Integer id);

    public int insertComment(Comment comment);

    public int updateComment(Comment comment);

    public int deleteComment(Integer movie_id,Integer user_id);
}
