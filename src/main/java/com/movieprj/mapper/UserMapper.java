package com.movieprj.mapper;

import com.movieprj.beans.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM user WHERE user_id =#{user_id}")
    public User findUserById(int id);

    @Insert( "INSERT INTO user (user_name,phone_number,password,user_email) " +
            "values (#{user_name},#{phone_number},#{password},#{user_email})" )
    void register(User user);
}
