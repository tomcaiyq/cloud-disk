package com.tomcai.redis.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    List<Object> list();

    List<Object> page(Integer begin, Integer offset);
}
