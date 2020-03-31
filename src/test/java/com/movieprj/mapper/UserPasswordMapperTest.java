package com.movieprj.mapper;

import com.movieprj.OurmovieprjApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= OurmovieprjApplication.class)
class UserPasswordMapperTest {

    @Resource
    private UserPasswordMapper userPasswordMapper;

    @Test
    void findByName() {
        System.out.println(userPasswordMapper.findByName("user1"));
    }

    @Test
    void findById() {
        System.out.println(userPasswordMapper.findById(1));
    }
}