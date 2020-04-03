package com.movieprj.beans;

public class Ticket_to_sell {
    private Integer ticket_id;
    private Integer movie_schedule_id;
    private Integer seat_id;
    private String status;

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getMovie_schedule_id() {
        return movie_schedule_id;
    }

    public void setMovie_schedule_id(Integer movie_schedule_id) {
        this.movie_schedule_id = movie_schedule_id;
    }

    public Integer getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(Integer seat_id) {
        this.seat_id = seat_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket_to_sell{" +
                "ticket_id=" + ticket_id +
                ", movie_schedule_id=" + movie_schedule_id +
                ", seat_id=" + seat_id +
                ", status='" + status + '\'' +
                '}';
    }
}
