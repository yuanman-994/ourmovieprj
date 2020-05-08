package com.movieprj.beans;

public class Message {
    private Integer message_id;
    private Integer user_id;
    private String content;
    private String time;
    private Integer status;
    private User user;

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", user_id=" + user_id +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }
}
