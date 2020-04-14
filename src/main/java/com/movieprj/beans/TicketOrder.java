package com.movieprj.beans;

import java.util.List;

public class TicketOrder {
    private Integer order_id;
    private Integer user_id;
    private Integer movie_schedule_id;
    private String time;
    private Float price;
    private String pay_way;
    private List<Ticket> ticketList;


    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getMovie_schedule_id() {
        return movie_schedule_id;
    }

    public void setMovie_schedule_id(Integer movie_schedule_id) {
        this.movie_schedule_id = movie_schedule_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPay_way() {
        return pay_way;
    }

    public void setPay_way(String pay_way) {
        this.pay_way = pay_way;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public String toString() {
        return "TicketOrder{" +
                "order_id=" + order_id +
                ", user_id=" + user_id +
                ", movie_schedule_id=" + movie_schedule_id +
                ", time='" + time + '\'' +
                ", price=" + price +
                ", pay_way='" + pay_way + '\'' +
                ", ticketList=" + ticketList +
                '}';
    }
}
