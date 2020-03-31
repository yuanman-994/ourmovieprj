package com.movieprj.mapper;

import com.movieprj.beans.UserPassword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserPasswordMapper {

    // 查询用户信息
    @Select("SELECT * FROM user_password WHERE user_name =#{name}")
    public UserPassword findByName(String name);


    public UserPassword findById(Integer id);
}