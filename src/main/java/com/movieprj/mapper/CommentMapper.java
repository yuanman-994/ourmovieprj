package com.movieprj.mapper;
import com.movieprj.beans.Comment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM movie_commen WHERE movie_id =#{movieId}")
    public List<Comment> findCommentByMovieId(Integer id);

    @Select("SELECT * FROM movie_commen WHERE movie_id =#{movieId}")
    @Results({
            @Result(id=true,property = "movie_id",column = "movie_id"),
            @Result(id=true,property = "user_id",column = "user_id"),
            @Result(property = "content",column = "content"),
            @Result(property = "comment_time",column = "comment_time"),
            @Result(property = "user",column = "user_id",one=@One(select="com.movieprj.mapper.UserMapper.findUserById",fetchType = FetchType.EAGER)),
    })
    public List<Comment> findCommentWithUserByMovieId(Integer id);

    @Insert("INSERT INTO movie_commen(movie_id,user_id,content,comment_time) " +
            "VALUES (#{movie_id},#{user_id},#{content},#{comment_time})")
    public int insertComment(Comment comment);

    @Update("UPDATE movie_commen SET content=#{content} WHERE user_id=#{user_id}")
    public int updateComment(Comment comment);

    @Delete("DELETE FROM movie_commen WHERE user_id=#{user_id}")
    public int deleteComment(Integer id);

}
