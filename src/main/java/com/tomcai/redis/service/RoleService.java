package com.tomcai.redis.service;

import java.util.List;

public interface RoleService {
    List<Object> getList(String key);

    List<Object> list(Integer pageNum, Integer pageSize);
}
