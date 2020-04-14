package com.movieprj.beans;


public class MoviesTypes {
    private  String type_name;   //类型名称
    private Integer movie_id;     //电影id


    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    @Override
    public String toString() {
        return "MoviesTypes{" +
                "type_name='" + type_name + '\'' +
                ", movie_id=" + movie_id +
                '}';
    }
}
