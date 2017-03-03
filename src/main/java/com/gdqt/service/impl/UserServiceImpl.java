package com.gdqt.service.impl;

import com.gdqt.dao.UserDao;
import com.gdqt.entity.User;
import com.gdqt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;


    public List<Map<String, Object>> getUser() {
        return userDao.getUser();
    }


    public User findUserByEmail(String s) {
        return userDao.findUserByEmail(s);
    }

    public User findUserByName(String s) {
        return userDao.findUserByName(s);
    }


}
