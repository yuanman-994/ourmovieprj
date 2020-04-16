package com.movieprj.services.groupbuy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieprj.beans.Cinema;
import com.movieprj.beans.GroupBuyBeans;
import com.movieprj.mapper.CinemaMapper;
import com.movieprj.mapper.GroupBuyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
//团购页面的服务函数
public class GroupBuyServiceImp implements GroupBuyService {
    @Resource
    private GroupBuyMapper groupBuyMapper;
    @Resource
    private CinemaMapper cinemaMapper;

    public String getGroupBuyDataNow() {//返回当期团购数据，json格式字符串
        List<GroupBuyBeans> groupBuyBeansData = groupBuyMapper.getGroupBuyDataNOW();
        List<GroupBuyPageComponent> finalData = new ArrayList<GroupBuyPageComponent>();
        this.loadDataFromBeans(groupBuyBeansData,finalData);
        JSONArray jsonArray = new JSONArray();
        for(int i = 0;i<finalData.size();i++){
            String js = JSON.toJSONString(finalData.get(i));
            JSONObject json = JSON.parseObject(js);
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }

    public String getGroupBuyDataPast() {//返回往期团购数据，json格式字符串
        List<GroupBuyBeans> groupBuyBeansData = groupBuyMapper.getGroupBuyDataPast();
        List<GroupBuyPageComponent> finalData = new ArrayList<GroupBuyPageComponent>();
        this.loadDataFromBeans(groupBuyBeansData,finalData);
        JSONArray jsonArray = new JSONArray();
        for(int i = 0;i<finalData.size();i++){
            String js = JSON.toJSONString(finalData.get(i));
            JSONObject json = JSON.parseObject(js);
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }

    //从一个GroupBuyBeans list 提取需要的信息，同步查询对应的影院信息，返回生成的GroupBuyPageComponent list
    public void loadDataFromBeans(List<GroupBuyBeans> groupBuyBeansData,List<GroupBuyPageComponent> finalData) {
        for (int i = 0; i < groupBuyBeansData.size(); i++) {
            GroupBuyPageComponent temp = new GroupBuyPageComponent();
            int cinema_id = groupBuyBeansData.get(i).getCinema_id();
            String cinema_name = this.groupBuyMapper.getCinemaNameById(cinema_id);
            this.loadGroupBuyBeans(groupBuyBeansData.get(i),temp);
            temp.cinema_name=cinema_name;
            temp.cinema_id=cinema_id;
            finalData.add(temp);
        }
    }

    public void loadGroupBuyBeans(GroupBuyBeans groupBuyBeans,GroupBuyPageComponent groupBuyPageComponent){//加载一个groupBuyBeans中的信息
        groupBuyPageComponent.group_buy_id=groupBuyBeans.getGroup_buy_id();
        groupBuyPageComponent.start_time=groupBuyBeans.getStart_time();
        groupBuyPageComponent.end_time=groupBuyBeans.getEnd_time();
        groupBuyPageComponent.price=groupBuyBeans.getPrice();
        groupBuyPageComponent.now_sales=groupBuyBeans.getNow_sales();
        groupBuyPageComponent.max_sales=groupBuyBeans.getMax_sales();
        groupBuyPageComponent.start_sell=groupBuyBeans.getStart_sell();
        groupBuyPageComponent.end_sell=groupBuyBeans.getEnd_sell();
    }

    @Override
    public String getCinema() {
        List<Cinema> data = cinemaMapper.findAll();
        JSONArray jsonArray = new JSONArray();
        for (Cinema c : data){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cinema_id",c.getCinema_id());
            jsonObject.put("cinema_name",c.getCinema_name());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public int saveGroupBuy(GroupBuyBeans groupBuyBeans) {
        try{
            groupBuyMapper.insertGroupBuy(groupBuyBeans);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public int updateGroupBuy(GroupBuyBeans groupBuyBeans) {
        try{
            groupBuyMapper.updateGroupBuy(groupBuyBeans);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public int stopSell(int[] ids) {

        try{
            for (int id : ids) {
                groupBuyMapper.stopSellById(id);
            }
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }
}
