package com.movieprj.beans;

public class Cinema {
    private Integer cinema_id;
    private String cinema_name;
    private String cinema_address;
    private String cinema_introduction;

    public Integer getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(Integer cinema_id) {
        this.cinema_id = cinema_id;
    }

    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public String getCinema_address() {
        return cinema_address;
    }

    public void setCinema_address(String cinema_address) {
        this.cinema_address = cinema_address;
    }

    public String getCinema_introduction() {
        return cinema_introduction;
    }

    public void setCinema_introduction(String cinema_introduction) {
        this.cinema_introduction = cinema_introduction;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinema_id=" + cinema_id +
                ", cinema_name='" + cinema_name + '\'' +
                ", cinema_address='" + cinema_address + '\'' +
                ", cinema_introduction='" + cinema_introduction + '\'' +
                '}';
    }
}
