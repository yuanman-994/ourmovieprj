package com.movieprj.dao;

public class GroupBuyDao {
    private int group_buy_id;
    private int cinema_id;
    private String start_time;
    private String end_time;
    private float price;
    private int now_sales;
    private int max_sales;
    private String start_sell;
    private String end_sell;

    public void setGroup_buy_id(int group_buy_id) {
        this.group_buy_id = group_buy_id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setNow_sales(int now_sales) {
        this.now_sales = now_sales;
    }

    public void setMax_sales(int max_sales) {
        this.max_sales = max_sales;
    }

    public void setStart_sell(String start_sell) {
        this.start_sell = start_sell;
    }

    public void setEnd_sell(String end_sell) {
        this.end_sell = end_sell;
    }

    public int getGroup_buy_id() {
        return group_buy_id;
    }

    public int getCinema_id() {
        return cinema_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public float getPrice() {
        return price;
    }

    public int getNow_sales() {
        return now_sales;
    }

    public int getMax_sales() {
        return max_sales;
    }

    public String getStart_sell() {
        return start_sell;
    }

    public String getEnd_sell() {
        return end_sell;
    }

    @Override
    public String toString() {
        return "GroupBuyDao{" +
                "group_buy_id=" + group_buy_id +
                ", cinema_id=" + cinema_id +
                ", strat_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", price=" + price +
                ", now_sales=" + now_sales +
                ", max_sales=" + max_sales +
                ", start_sell='" + start_sell + '\'' +
                ", end_sell='" + end_sell + '\'' +
                '}';
    }
}
