package com.tomcai.cloud.service;

import com.tomcai.cloud.pojo.User;

public interface UserService {
    User getByUsername(String username);

    String getPwdByUsername(String username);

    boolean add(User user);
}
