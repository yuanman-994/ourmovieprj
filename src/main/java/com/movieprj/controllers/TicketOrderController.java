package com.movieprj.controllers;


import com.movieprj.beans.TicketOrder;
import com.movieprj.services.TicketOrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TicketOrderController {

    @Autowired
    private TicketOrderServiceImp ticketOrderService;

    @RequestMapping("/getOrderById")
    @ResponseBody
    public Map<String,Object> selectMoviesWithTypes(Integer order_id){
        TicketOrder ticketOrder = ticketOrderService.findTicketOrderWithTicketsById(order_id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ticketOrder",ticketOrder);
        return map;
    }
}
