package com.movieprj.services;

import com.movieprj.beans.User;
import com.movieprj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserServiceImpl implements UserServices {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User regist(User user) {
        userMapper.register(user);
        return user;
    }
}
