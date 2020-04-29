package com.movieprj.beans;

public class MovieSchedule {
    private Integer movie_schedule_id;
    private Integer cinema_id;
    private Integer hall_id;
    private Integer movie_id;
    private String show_type;
    private Float price;
    private String start_sell;
    private String end_sell;
    private Movies movie;
    private Cinema cinema;
    private Hall hall;

    public Integer getMovie_schedule_id() {
        return movie_schedule_id;
    }

    public void setMovie_schedule_id(Integer movie_schedule_id) {
        this.movie_schedule_id = movie_schedule_id;
    }

    public Integer getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(Integer cinema_id) {
        this.cinema_id = cinema_id;
    }

    public Integer getHall_id() {
        return hall_id;
    }

    public void setHall_id(Integer hall_id) {
        this.hall_id = hall_id;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStart_sell() {
        return start_sell;
    }

    public void setStart_sell(String start_sell) {
        this.start_sell = start_sell;
    }

    public String getEnd_sell() {
        return end_sell;
    }

    public void setEnd_sell(String end_sell) {
        this.end_sell = end_sell;
    }

    @Override
    public String toString() {
        return "MovieSchedule{" +
                "movie_schedule_id=" + movie_schedule_id +
                ", cinema_id=" + cinema_id +
                ", hall_id=" + hall_id +
                ", movie_id=" + movie_id +
                ", show_type='" + show_type + '\'' +
                ", price=" + price +
                ", start_sell='" + start_sell + '\'' +
                ", end_sell='" + end_sell + '\'' +
                '}';
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
