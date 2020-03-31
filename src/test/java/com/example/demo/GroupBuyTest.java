package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.movieprj.OurmovieprjApplication;
import com.movieprj.beans.GroupBuyBeans;
import com.movieprj.mapper.GroupBuyMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes= OurmovieprjApplication.class)
public class GroupBuyTest {
    @Resource
    private GroupBuyMapper groupBuyMapper;

    @Test
    public void SelectGroupBuy(){
        List<GroupBuyBeans> groupBuy = groupBuyMapper.getAllGroupBuyData();
        System.out.println(groupBuy.get(3));
    }
    @Test
    public void testJson(){
        JSONObject js = new JSONObject();
        js.put("test",1);
        System.out.println(js);
    }
}
