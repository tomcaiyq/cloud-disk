package com.tomcai.cloud.dao;

import java.util.List;

public interface BaseDao<T> {
    T getById(String id);

    int insert(T t);

    List<T> list();

    List<T> listByType(String type);

    int delete(String id);

    int update(T t);
}
