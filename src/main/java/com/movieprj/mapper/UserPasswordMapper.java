package com.movieprj.mapper;

import com.movieprj.beans.Permission;
import com.movieprj.beans.Role;
import com.movieprj.beans.UserPassword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserPasswordMapper {

    // 查询用户信息
    @Select("SELECT * FROM user_password WHERE user_name =#{name}")
    public UserPassword findByName(String name);
    public UserPassword findById(Integer uid);//多表联合查询，user_password,role,permission
    public Role findRoleById (Integer rid);//role permisssion两表联合查询
    public List<Permission> findRolePermissionById(Integer rid);//从role_permission,permission中查找role_id为rid的role的permission
    public List<Role> findUserRoleById(Integer uid);//从user_role,role中查找user_id为uid的user的role
}