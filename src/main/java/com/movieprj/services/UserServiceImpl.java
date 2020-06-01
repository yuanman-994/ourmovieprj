package com.movieprj.services;

import com.movieprj.beans.User;
import com.movieprj.beans.UserPassword;
import com.movieprj.mapper.UserMapper;
import com.movieprj.mapper.UserPasswordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserPasswordMapper userPasswordMapper;

    @Override
    public User regist(User user) {
        userMapper.register(user);
        return user;
    }

    @Override
    public int changeName(String name, String newName) {
        //查询用户id
        int uid = userPasswordMapper.findIdByName(name);

        if (name.equals(newName)){
            return -2;
        }
        //查询新名字是否有重复
        try {
            userPasswordMapper.findIdByName(newName);
        } catch (org.apache.ibatis.binding.BindingException e){//如果报这个错表示名字未重复
            userPasswordMapper.updateNameById(newName,uid);
            return 0;
        }
        return -1;//名字重复
    }

    @Override
    public int changePassword(String name, String password, String newPassword) {
        int uid = userPasswordMapper.findIdByName(name);
        UserPassword user = userPasswordMapper.findById(uid);
        String truePassword = user.getPassword();
        if (!truePassword.equals(password))
            return -1;//密码错误
        userPasswordMapper.updatePasswordById(password,uid);
        return 0;
    }
}
