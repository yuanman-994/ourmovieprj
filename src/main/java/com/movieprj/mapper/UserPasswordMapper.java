package com.movieprj.mapper;

import com.movieprj.beans.Permission;
import com.movieprj.beans.Role;
import com.movieprj.beans.UserPassword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserPasswordMapper {

    // 查询用户信息
    @Select("SELECT user_id FROM user_password WHERE user_name = #{name}")
    public int findIdByName(String name);//以用户名查找用户id

    @Select("SELECT user_name FROM user_password WHERE user_id = #{id}")
    public String findNameById(int id);

    public UserPassword findById(Integer uid);//多表联合查询，user_password,role,permission
    public Role findRoleById (Integer rid);//role permisssion两表联合查询
    public List<Permission> findRolePermissionById(Integer rid);//从role_permission,permission中查找role_id为rid的role的permission
    public List<Role> findUserRoleById(Integer uid);//从user_role,role中查找user_id为uid的user的role

    @Update("UPDATE user_password SET user_name=#{user_name} WHERE user_id=#{user_id}")
    public void updateNameById(String user_name,int user_id);

    @Update("UPDATE user_password SET password=#{password} WHERE user_id=#{user_id}")
    public void updatePasswordById(String password,int user_id);
}