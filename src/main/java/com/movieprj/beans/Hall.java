package com.movieprj.beans;

import java.util.List;

public class Hall {
    private Integer hall_id;
    private Integer cinema_id;
    private Integer number_of_seats;
    private List<Seat> seatList;
    private String seat_map;

    public Integer getHall_id() {
        return hall_id;
    }

    public void setHall_id(Integer hall_id) {
        this.hall_id = hall_id;
    }

    public Integer getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(Integer cinema_id) {
        this.cinema_id = cinema_id;
    }

    public Integer getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(Integer number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public String getSeat_map() {
        return seat_map;
    }

    public void setSeat_map(String seat_map) {
        this.seat_map = seat_map;
    }
}
