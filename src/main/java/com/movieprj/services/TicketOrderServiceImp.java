package com.movieprj.services;

import com.movieprj.beans.Ticket;
import com.movieprj.beans.TicketOrder;
import com.movieprj.mapper.TicketOrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TicketOrderServiceImp implements TicketOrderService {

    @Resource
    private  TicketOrderMapper ticketOrderMapper;


    @Override
    public TicketOrder findTicketOrderWithTicketsById(Integer order_id) {
        return ticketOrderMapper.findTicketOrderWithTicketsById(order_id);
    }

    @Override
    public int addTicketOrder(TicketOrder ticketOrder) {
        ticketOrderMapper.addTicketOrder(ticketOrder);
        return ticketOrder.getOrder_id();
    }

    @Override
    public int deleteTicketOrder(Integer order_id) {
        ticketOrderMapper.deleteTicketOrder(order_id);
        return 1;
    }

    @Override
    public int updateTicketOrderStatus(Integer order_id, String pay_way) {
        ticketOrderMapper.updateTicketOrderStatus(order_id,pay_way);
        return 1;
    }
}
