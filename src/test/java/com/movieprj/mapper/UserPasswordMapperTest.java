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
    }

    @Test
    void findById() {
        System.out.println(userPasswordMapper.findById(1));
    }

    @Test
    void findRoleById() {
        System.out.println(userPasswordMapper.findRoleById(1));
    }

    @Test
    void findRolePermissionById() {
        System.out.println(userPasswordMapper.findRolePermissionById(2));
    }

    @Test
    void findUserRoleById() {
        System.out.println(userPasswordMapper.findUserRoleById(1));
    }
}