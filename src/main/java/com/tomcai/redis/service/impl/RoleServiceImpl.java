package com.tomcai.redis.service.impl;

import com.tomcai.redis.dao.RoleDao;
import com.tomcai.redis.service.RedisService;
import com.tomcai.redis.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RedisService redisService;

    @Resource
    private RoleDao roleDao;

    @Override
    public List<Object> getList(String key) {
        if (redisService.hasKey(key)) {
            System.out.println("redis取得数据");
            return redisService.list(key);
        } else {
            System.out.println("mysql取得数据");
            List<Object> list = roleDao.list();
            redisService.lpush(key, list);
            return list;
        }
    }

    @Override
    public List<Object> list(Integer pageNum, Integer pageSize) {
        Integer begin = (pageNum - 1) * pageSize;
        Integer offset = pageSize * pageNum;
        StringBuilder sb = new StringBuilder("page:");
        sb.append(pageNum);
        if (redisService.hasKey(sb.toString())) {
            System.out.println("redis取得数据");
            return redisService.list(sb.toString());
        } else {
            System.out.println("mysql取得数据");
            List<Object> list = roleDao.list();
            redisService.lpush(sb.toString(), list);
            return roleDao.page(begin, offset);
        }

    }
}
