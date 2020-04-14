package com.movieprj.services;

import com.movieprj.beans.Ticket;

import java.util.List;

public interface TicketService {

    public int addTicket(Ticket ticket);

    public List<Ticket> findTicketsByOrderId(Integer order_id);
}
