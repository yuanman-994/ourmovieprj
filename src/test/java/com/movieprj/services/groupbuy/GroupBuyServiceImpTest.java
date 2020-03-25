package com.movieprj.services.groupbuy;

import com.movieprj.OurmovieprjApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes= OurmovieprjApplication.class)
class GroupBuyServiceImpTest {
    @Autowired
    private GroupBuyServiceImp groupBuyServiceImp;
    @Test
    void getGroupBuyDataNow() {
        System.out.println(groupBuyServiceImp.getGroupBuyDataNow());
    }

    @Test
    void getGroupBuyDataPast() {
    }

    @Test
    void loadDataFromBeans() {
    }
}