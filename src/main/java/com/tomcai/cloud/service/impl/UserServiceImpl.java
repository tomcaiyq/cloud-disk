package com.tomcai.cloud.service.impl;

import com.tomcai.cloud.pojo.User;
import com.tomcai.cloud.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getByUsername(String username) {
        return null;
    }
}
