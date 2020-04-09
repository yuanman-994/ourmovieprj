package com.movieprj.services;

import com.movieprj.beans.TicketOrder;

public interface TicketOrderService {

    public TicketOrder findTicketOrderWithTicketsById(Integer order_id);

    public int addTicketOrder(TicketOrder ticketOrder);
}
