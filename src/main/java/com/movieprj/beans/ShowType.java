package com.movieprj.beans;

public class ShowType {
    private String show_type;

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    @Override
    public String toString() {
        return "ShowType{" +
                "show_type='" + show_type + '\'' +
                '}';
    }
}
