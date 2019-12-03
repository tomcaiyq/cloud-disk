package com.tomcai.cloud.service.impl;

import com.tomcai.cloud.dao.UserDao;
import com.tomcai.cloud.pojo.User;
import com.tomcai.cloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
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

    @Override
    public boolean add(User user) {
        int i;
        try {
            i = userDao.insert(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        return i > 0;
    }
}
