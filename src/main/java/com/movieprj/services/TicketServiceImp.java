package com.movieprj.services;

import com.movieprj.beans.Ticket;
import com.movieprj.mapper.TicketMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TicketServiceImp implements TicketService {

    @Resource
    private TicketMapper ticketMapper;

    @Override
    public int addTicket(Ticket ticket) {
        ticketMapper.addTicket(ticket);
        return 1;
    }

    @Override
    public List<Ticket> findTicketsByOrderId(Integer order_id) {
        return ticketMapper.findTicketsByOrderId(order_id);
    }
}
