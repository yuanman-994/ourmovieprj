package com.movieprj.controllers;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.movieprj.beans.GroupBuyBeans;
import com.movieprj.beans.GroupBuyOrder;
import com.movieprj.beans.GroupBuyOrderTemp;
import com.movieprj.mapper.GroupBuyMapper;
import com.movieprj.util.AlipayConfig;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PayController {
    @Resource
    private GroupBuyMapper groupBuyMapper;

    @RequestMapping(value = "/groupBuy/groupBuyPay/alipay",method = RequestMethod.GET)
    @ResponseBody
    public  String   Alipay(@RequestParam("order_id") int order_id){
        // 获得一个支付宝请求的客户端(它并不是一个链接，而是一个封装好的http的表单请求)
        String form = null;
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);

        // 回调函数
        alipayRequest.setReturnUrl(AlipayConfig.GROUP_BUY_RETURN_URL);
        alipayRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);

        GroupBuyOrderTemp groupBuyOrderTemp = groupBuyMapper.getGroupBuyOrderTempBuyId(order_id);
        if (groupBuyOrderTemp==null)
            return form;

        String orderCreatTime = groupBuyOrderTemp.getTime();//交易创建时间
//        System.out.println(orderCreatTime);
        long time = Timestamp.valueOf(orderCreatTime).getTime();
        time += 300000;//设置绝对超时时间为交易创建时间5分钟后（360000毫秒）但是数据库时间似乎慢了2分钟，实际超时时间是三分钟
//        System.out.println(time);
        Timestamp timestamp = new Timestamp(time);
//        System.out.println(timestamp);
        String time_expire = timestamp.toString();
//        System.out.println(time_expire);

//        System.out.println("uuid"+groupBuyOrderTemp.getUuid());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("out_trade_no", groupBuyOrderTemp.getUuid());
        map.put("product_code","FAST_INSTANT_TRADE_PAY");
        map.put("total_amount",groupBuyOrderTemp.getTotal_price());

        map.put("time_expire",time_expire);

        map.put("subject","华莱坞影城团购票");

        String param = JSON.toJSONString(map);

        alipayRequest.setBizContent(param);

        try {

            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
//            System.out.println(form);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }

    /*
      统一收单线下交易查询
      */
    @RequestMapping(value = "/alipayQuery",method = RequestMethod.GET)
    @ResponseBody
    public  String  AlipayQuery() throws AlipayApiException {
        // 获得一个支付宝请求的客户端(它并不是一个链接，而是一个封装好的http的表单请求)
        String form = null;
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();//创建API对应的request

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);

        // 回调函数
        alipayRequest.setReturnUrl(AlipayConfig.GROUP_BUY_RETURN_URL);
        alipayRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("out_trade_no","c55c1b07-dea7-4066-920f-c56239a5e946");
        String param = JSON.toJSONString(map);
        alipayRequest.setBizContent(param);
        AlipayTradeQueryResponse response = alipayClient.execute(alipayRequest);
        String msg = response.getMsg();
//        System.out.println(msg);
//        System.out.println(response);
        return  msg;
    }


    @Scheduled(fixedDelay=10*1000)
    public void QueryOrder(){//定时查询订单状态
        List<GroupBuyOrderTemp> groupBuyOrderTemps = groupBuyMapper.getAllGroupBuyOrderTemp();
//        System.out.println(groupBuyOrderTemps);
        long now = System.currentTimeMillis();//当前系统时间（毫秒）
        for (GroupBuyOrderTemp groupBuyOrderTemp : groupBuyOrderTemps){
            String time = groupBuyOrderTemp.getTime();//订单创建时间
            long time_expire = Timestamp.valueOf(time).getTime()+600*1000;//该订单的绝对超时时间（毫秒） 10分钟
            if (time_expire<now){
//                System.out.println("检测到临时订单"+groupBuyOrderTemp.getId()+"已超时");
                if (IsPaySuccess(groupBuyOrderTemp)){//检测已超时的订单检查是否已完成支付
                    System.out.println("订单"+groupBuyOrderTemp.getId()+"已完成支付,删除临时订单，转存正式订单数据库");
                    MoveOrder(groupBuyOrderTemp);
                } else {
                    System.out.println("订单"+groupBuyOrderTemp.getId()+"已超时且未完成支付，现取消，并将重设票余量");
                    cancelOrder(groupBuyOrderTemp);
                }
            }
        }
    }

    public boolean IsPaySuccess(GroupBuyOrderTemp groupBuyOrderTemp) {

        // 获得一个支付宝请求的客户端(它并不是一个链接，而是一个封装好的http的表单请求)
        String form = null;
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();//创建API对应的request

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY_URL, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);

        // 回调函数
        alipayRequest.setReturnUrl(AlipayConfig.GROUP_BUY_RETURN_URL);
        alipayRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("out_trade_no",groupBuyOrderTemp.getUuid());
        String param = JSON.toJSONString(map);
        alipayRequest.setBizContent(param);
        String code;
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(alipayRequest);
            code = response.getCode();
//            System.out.println("订单"+groupBuyOrderTemp.getId()+":"+msg);
        } catch (Exception e){
            return false;
        }
//        System.out.println(code);
        if (Integer.parseInt(code)==10000)//支付成功
            return true;
        else
            return false;
    }

    @Transactional(isolation= Isolation.REPEATABLE_READ )
    public void MoveOrder(GroupBuyOrderTemp groupBuyOrderTemp){//转存已完成支付的临时订单至订单表
        GroupBuyOrder groupBuyOrder = new GroupBuyOrder();
        groupBuyOrder.setGroup_buy_id(groupBuyOrderTemp.getGroup_buy_id());
        groupBuyOrder.setPrice(groupBuyOrderTemp.getPrice());
        groupBuyOrder.setTicket_num(groupBuyOrderTemp.getNum());
        groupBuyOrder.setTotal_price(groupBuyOrder.getTotal_price());
        groupBuyOrder.setUser_id(groupBuyOrderTemp.getUser_id());
        groupBuyOrder.setUuid(groupBuyOrderTemp.getUuid());
        groupBuyOrder.setVerification_code("验证码未完成");
        groupBuyMapper.insertGroupBuyOrder(groupBuyOrder);
        groupBuyMapper.deleteGroupBuyOrderTempBuyId(groupBuyOrderTemp.getId());
    }

    @Transactional(isolation= Isolation.REPEATABLE_READ )
    public void cancelOrder(GroupBuyOrderTemp groupBuyOrderTemp){
        int num = groupBuyOrderTemp.getNum();
        GroupBuyBeans groupBuyBeans = groupBuyMapper.findById(groupBuyOrderTemp.getGroup_buy_id());
        groupBuyBeans.setNow_sales(groupBuyBeans.getNow_sales()-num);
        groupBuyMapper.updateNowSales(groupBuyBeans);
        groupBuyMapper.deleteGroupBuyOrderTempBuyId(groupBuyOrderTemp.getId());
    }
}
