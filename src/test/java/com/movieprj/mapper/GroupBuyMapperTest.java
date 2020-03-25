package com.movieprj.mapper;

import com.movieprj.OurmovieprjApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= OurmovieprjApplication.class)
class GroupBuyMapperTest {
    @Resource
    private GroupBuyMapper groupBuyMapper;
    @Test
    void findById() {
        System.out.println(groupBuyMapper.findById(1));
    }

    @Test
    void getAllGroupBuyData() {
        System.out.println(groupBuyMapper.getAllGroupBuyData());
    }

    @Test
    void getGroupBuyDataNOW() {
        System.out.println(groupBuyMapper.getGroupBuyDataNOW());
    }

    @Test
    void getGroupBuyDataPast() {
        System.out.println(groupBuyMapper.getGroupBuyDataPast());
    }

    @Test
    void getCinemaNameById() {
        System.out.println(groupBuyMapper.getCinemaNameById(1));
    }
}