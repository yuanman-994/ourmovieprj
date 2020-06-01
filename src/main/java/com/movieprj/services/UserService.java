package com.movieprj.services;

import com.movieprj.beans.User;

public interface UserService {
    public User regist(User user);
    int changeName(String name,String newName);
    int changePassword(String name,String password,String newPassword);
}
