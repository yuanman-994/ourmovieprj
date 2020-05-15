package com.movieprj.controllers;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.movieprj.beans.TicketOrder;
import com.movieprj.config.AlipayConfig;
import com.movieprj.services.TicketOrderServiceImp;
import com.movieprj.services.TicketServiceImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AlipayController {

    @Autowired
    private TicketOrderServiceImp ticketOrderService;

    @Autowired
    private TicketServiceImp ticketService;

    @RequestMapping("/pay")
    public String topay(Integer order_id){
        return "redirect:/pay1?order_id="+order_id;
    }

    @RequestMapping("/pay1")
    public String pay(Integer order_id){
        return "pay";
    }

    @RequestMapping("paysuccess")
    public String paysuccess(){return "paysuccess";}

    @RequestMapping("/toPay")
    @ResponseBody
    public String toPay(HttpServletRequest request) throws Exception{
        TicketOrder ticketOrder = new TicketOrder();
        String pay_way=null;
        if(request.getParameter("order_id")!=null){
            Integer order_id = Integer.valueOf(request.getParameter("order_id").toString());
            ticketOrder = ticketOrderService.findTicketOrderWithTicketsById(order_id);
        }

        if(request.getParameter("pay_way")!=null){//确定支付方式
            pay_way = request.getParameter("pay_way").toString();
        }
        if(pay_way.equals("alipay")) {
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(AlipayConfig.return_url);  //支付完成后返回的页面
            alipayRequest.setNotifyUrl(AlipayConfig.notify_url);//支付宝服务器通知商户相关信息

            Integer no = ticketOrder.getOrder_id();
            Float price = ticketOrder.getPrice();
            Integer schedule_id = ticketOrder.getMovie_schedule_id();
            String time = ticketOrder.getTime();
            Calendar calendar=Calendar.getInstance();
            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start_date = simpleDate.parse(time); //订单下单时间
            calendar.setTime(start_date);
            calendar.add(Calendar.MINUTE,10);
            Date end_date = calendar.getTime();  //订单超时时间
            Date now = new Date();  //当前时间
            long diff = end_date.getTime()-now.getTime();
            int minutes = (int)diff/(1000*60);
            String timeout_express = minutes+"m";//超时时间
            try {
                alipayRequest.setBizContent("{\"out_trade_no\":\"" + no + "\","
                        + "\"total_amount\":\"" + price + "\","
                        + "\"subject\":\"" + "电影票" + "\","
                        + "\"timeout_express\":\"" + timeout_express + "\","
                        + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

                //请求
                String result;
                result = alipayClient.pageExecute(alipayRequest).getBody();
                System.out.println("*********************\n返回结果为：" + result);
                return result;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }//支付宝支付

        return null;
    }

    @RequestMapping("/alipayReturnNotice")
    public ModelAndView alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {
         Logger log = LogManager.getLogger(this.getClass());
        log.info("支付成功, 进入同步通知接口...");
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            /*valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");*/
            params.put(name, valueStr);
        }

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        ModelAndView mv = new ModelAndView("paysuccess");
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            // 修改订单状态为支付成功，已付款; 同时新增支付流水
            ticketOrderService.updateTicketOrderStatus(Integer.valueOf(out_trade_no),"alipay");

            TicketOrder order = ticketOrderService.findTicketOrderWithTicketsById(Integer.valueOf(out_trade_no));

            log.info("********************** 支付成功(支付宝同步通知) **********************");
            log.info("* 订单号: {}", out_trade_no);
            log.info("* 支付宝交易号: {}", trade_no);
            log.info("* 实付金额: {}", total_amount);
            log.info("* 购买产品: {}", order.getMovie_schedule_id());
            log.info("***************************************************************");
            mv.addObject("out_trade_no", out_trade_no);
            mv.addObject("trade_no", trade_no);
            mv.addObject("total_amount", total_amount);
            mv.addObject("schedule", order.getMovie_schedule_id());
        }else {
            log.info("支付, 验签失败...");
        }
        return mv;
    }


    @RequestMapping("/alipayNotifyNotice")
    @ResponseBody
    public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

        Logger log = LogManager.getLogger(this.getClass());
        log.info("支付成功, 进入异步通知接口...");

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            /*valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");*/
            params.put(name, valueStr);
        }

        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);

        //——请在这里编写您的程序（以下代码仅作参考）——

   /* 实际验证过程建议商户务必添加以下校验：
   1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
   2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
   3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
   4、验证app_id是否为该商户本身。
   */
        //验证成功
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

                // 修改订单状态，改为 支付成功，已付款; 同时新增支付流水
                ticketOrderService.updateTicketOrderStatus(Integer.valueOf(out_trade_no),"alipay");

                TicketOrder order = ticketOrderService.findTicketOrderWithTicketsById(Integer.valueOf(out_trade_no));

                log.info("********************** 支付成功(支付宝异步通知) **********************");
                log.info("* 订单号: {}", out_trade_no);
                log.info("* 支付宝交易号: {}", trade_no);
                log.info("* 实付金额: {}", total_amount);
                log.info("* 购买产品: {}", order.getMovie_schedule_id());
                log.info("***************************************************************");
            }
            log.info("支付成功...");
        }else {//验证失败
            log.info("支付, 验签失败...");
        }
        return "success";
    }

    @RequestMapping("alipay/callback/return_sult")
    public String toIndex(){
        return "index";
    }

}
