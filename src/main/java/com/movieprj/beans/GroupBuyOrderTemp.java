package com.movieprj.beans;

public class GroupBuyOrderTemp {
    private int id;
    private int group_buy_id;//团购id
    private int user_id;
    private int num;
    private float price;
    private float total_price;
    private String time;

    @Override
    public String toString() {
        return "GroupBuyOrderTemp{" +
                "id=" + id +
                ", group_buy_id=" + group_buy_id +
                ", user_id=" + user_id +
                ", num=" + num +
                ", price=" + price +
                ", total_price=" + total_price +
                ", time='" + time + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_buy_id() {
        return group_buy_id;
    }

    public void setGroup_buy_id(int group_buy_id) {
        this.group_buy_id = group_buy_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
