package com.gdqt.service;


import com.gdqt.entity.User;

public interface UserService {
    User findUserByEmail(String s);

    User findUserByName(String s);
}
