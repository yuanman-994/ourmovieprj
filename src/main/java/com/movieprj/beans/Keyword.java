package com.movieprj.beans;

public class Keyword {
    private int pid;
    private String content;

    public Keyword(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
}
