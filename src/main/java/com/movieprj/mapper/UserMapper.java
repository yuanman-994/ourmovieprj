package com.movieprj.mapper;

import com.movieprj.beans.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM user WHERE user_id =#{user_id}")
    public User findUserById(int id);

    @Insert( "INSERT INTO user (user_name) " +
            "values (#{user_name})" )
    public void register(User user);

    @Select("SELECT user_id FROM user WHERE user_name = #{name}")
    public int findIdByName(String name);

    @Select("SELECT user_name FROM user WHERE user_id = #{id}")
    public String findNameById(int id);

    @Update("UPDATE user SET user_name=#{user_name} WHERE user_id=#{user_id}")
    public void updateNameById(String user_name,int user_id);

    @Update("UPDATE user SET phone_number=#{phone_number},user_email=#{user_email} WHERE user_name=#{user_name}")
    public void updateUser(User user);
}
