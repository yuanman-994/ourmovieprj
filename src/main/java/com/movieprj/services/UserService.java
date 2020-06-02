package com.movieprj.services;

import com.movieprj.beans.User;

public interface UserService {
    String regist(User user);
    int changeName(String name,String newName);
    int changePassword(String name,String password,String newPassword);
    String getUserInf(String name);
    int setUserInf(String name,User user);
}
