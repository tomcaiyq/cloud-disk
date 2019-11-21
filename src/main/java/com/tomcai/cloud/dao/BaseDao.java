package com.tomcai.cloud.dao;

import java.util.List;

public interface BaseDao<T> {
    // 根据条件查询单个
    T find(T t);

    // 根据条件查询多个
    List<T> list(T t);

    // 新增
    int insert(T t);

    // 删除
    int delete(T t);

    // 更新
    int update(T t);
}
