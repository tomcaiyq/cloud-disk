package com.tomcai.cloud.service.impl;

import com.tomcai.cloud.dao.UserDao;
import com.tomcai.cloud.pojo.User;
import com.tomcai.cloud.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userDao.find(user);
    }

    @Override
    public String getPwdByUsername(String username) {
        return userDao.getPwdByUsername(username);
    }
}
