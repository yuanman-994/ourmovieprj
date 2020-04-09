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
        return 1;
    }
}
