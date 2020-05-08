package com.movieprj.mapper;


import com.movieprj.beans.Message;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Select("SELECT * FROM message_board WHERE message_id =#{message_id}")
    @Results({
            @Result(id=true,property = "message_id",column = "message_id"),
            @Result(property = "user_id",column = "user_id"),
            @Result(property = "content",column = "content"),
            @Result(property = "time",column = "time"),
            @Result(property = "status",column = "status"),
            @Result(property = "user",column = "user_id",one=@One(select="com.movieprj.mapper.UserMapper.findUserById",fetchType = FetchType.EAGER)),
    })
    public Message findMessageWithUserById(Integer message_id);

    @Select("SELECT * FROM message_board ")
    @Results({
            @Result(id=true,property = "message_id",column = "message_id"),
            @Result(property = "user_id",column = "user_id"),
            @Result(property = "content",column = "content"),
            @Result(property = "time",column = "time"),
            @Result(property = "status",column = "status"),
            @Result(property = "user",column = "user_id",one=@One(select="com.movieprj.mapper.UserMapper.findUserById",fetchType = FetchType.EAGER)),
    })
    public List<Message> findAllMessageWithUser();

    @Insert("INSERT INTO message_board (user_id,content,time,status) "+
            "VALUES (#{user_id},#{content},#{time},#{status})")
    @Options(useGeneratedKeys=true, keyProperty="message_id", keyColumn="message_id")
    public int addMessage(Message message);

    @Delete("DELETE FROM message_board WHERE message_id = #{message_id}")
    public int deleteMessage(Integer message_id);

    @Update("Update message_board set " +
            "user_id = #{user_id},"+
            "content = #{content},"+
            "time = #{time},"+
            "status = #{status} "+
            "WHERE message_id = #{message_id}"
    )
    public int updateMessage(Message message);
}
