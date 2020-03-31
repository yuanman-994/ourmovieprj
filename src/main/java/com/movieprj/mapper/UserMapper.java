package com.movieprj.mapper;

import com.movieprj.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE user_id =#{user_id}")
    public User findUserById(int id);
}
