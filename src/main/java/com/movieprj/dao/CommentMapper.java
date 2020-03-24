package com.movieprj.dao;
import com.movieprj.beans.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM movie_commen WHERE movie_id =#{movieId}")
    public List<Comment> findCommentByMovieId(Integer id);

    @Insert("INSERT INTO movie_commen(movie_id,user_id,content,commen_time) " +
            "values (#{movie_id},#{user_id},#{content},#{commen_time})")
    public int insertComment(Comment comment);

    @Update("UPDATE movie_commen SET content=#{content} WHERE user_id=#{user_id}")
    public int updateComment(Comment comment);

    @Delete("DELETE FROM movie_commen WHERE user_id=#{user_id}")
    public int deleteComment(Integer id);
}
