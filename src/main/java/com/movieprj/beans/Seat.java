package com.movieprj.beans;

public class Seat {
    private Integer seat_id;  //座位id
    private Integer hall_id;  //影厅id
    private Integer loc_x;    //行坐标
    private Integer loc_y;    //列坐标

    public Integer getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(Integer seat_id) {
        this.seat_id = seat_id;
    }

    public Integer getHall_id() {
        return hall_id;
    }

    public void setHall_id(Integer hall_id) {
        this.hall_id = hall_id;
    }

    public Integer getLoc_x() {
        return loc_x;
    }

    public void setLoc_x(Integer loc_x) {
        this.loc_x = loc_x;
    }

    public Integer getLoc_y() {
        return loc_y;
    }

    public void setLoc_y(Integer loc_y) {
        this.loc_y = loc_y;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seat_id=" + seat_id +
                ", hall_id=" + hall_id +
                ", loc_x=" + loc_x +
                ", loc_y=" + loc_y +
                '}';
    }
}
