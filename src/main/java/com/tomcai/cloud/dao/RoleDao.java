package com.tomcai.cloud.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    List<Object> list();

    List<Object> page(Integer offset, Integer pageSize);
}
