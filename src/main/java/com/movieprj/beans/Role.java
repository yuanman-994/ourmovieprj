package com.movieprj.beans;

import java.util.HashSet;
import java.util.Set;

//普通用户（非后台用户），角色类
public class Role {
    private int role_id;//角色id
    private String role_name;//角色名，（普通，VIP等）
    private Set<Permission> permissions = new HashSet<>();

    public int getRole_id() {
        return role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                '}';
    }
}
