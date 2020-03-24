package com.example.demo;

import com.movieprj.OurmovieprjApplication;
import com.movieprj.dao.GroupBuyDao;
import com.movieprj.mapper.GroupyBuyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes= OurmovieprjApplication.class)
public class GropuBuyTest {
    @Resource
    private GroupyBuyMapper groupyBuyMapper;
    @Test
    public void SelectGroupBuy(){
        GroupBuyDao groupBuy = groupyBuyMapper.findById(1);
        System.out.println(groupBuy);
    }
}
