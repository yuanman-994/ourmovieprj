package com.movieprj.services;

import com.movieprj.beans.UserPassword;
import com.movieprj.mapper.UserPasswordMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserPasswordMapper userPasswordMapper;

    @Override
    public UserPassword getUserByName(String name) {
        int uid = userPasswordMapper.findIdByName(name);
        return userPasswordMapper.findById(uid);
    }

}
