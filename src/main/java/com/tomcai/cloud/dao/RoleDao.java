package com.tomcai.cloud.dao;

import com.tomcai.cloud.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends BaseDao<Role> {
    List<Object> list();

    List<Object> page(Integer offset, Integer pageSize);
}
