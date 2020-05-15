package com.movieprj.beans;

public class GroupBuyOrder {
    private int order_id;
    private int group_buy_id;//团购id
    private int user_id;
    private int ticket_num;
    private float price;
    private float total_price;
    private String uuid;
    private String time;
    private String verification_code;//验证码

    @Override
    public String toString() {
        return "GroupBuyOrder{" +
                "order_id=" + order_id +
                ", group_buy_id=" + group_buy_id +
                ", user_id=" + user_id +
                ", ticket_num=" + ticket_num +
                ", price=" + price +
                ", total_price=" + total_price +
                ", uuid='" + uuid + '\'' +
                ", time='" + time + '\'' +
                ", verification_code='" + verification_code + '\'' +
                '}';
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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

    public int getTicket_num() {
        return ticket_num;
    }

    public void setTicket_num(int ticket_num) {
        this.ticket_num = ticket_num;
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
