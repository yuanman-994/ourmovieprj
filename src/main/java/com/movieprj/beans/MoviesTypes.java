package com.movieprj.beans;


public class MoviesTypes {
    private  Integer type_id;   //类型id
    private String name;     //类型名称

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MoviesTypes{" +
                "typeid='" + type_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
