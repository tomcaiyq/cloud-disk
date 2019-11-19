package com.tomcai.redis.service.impl;

import com.tomcai.redis.dao.RoleDao;
import com.tomcai.redis.service.RedisService;
import com.tomcai.redis.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
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
        Integer offset = (pageNum - 1) * pageSize;
        StringBuilder sb = new StringBuilder("page:");
        sb.append(pageNum);
        if (redisService.hasKey(sb.toString())) {
            long t1 = System.currentTimeMillis();
            List<Object> list = redisService.list(sb.toString());
            long t2 = System.currentTimeMillis();
            log.info("从redis取得数据,耗时:" + (t2 - t1) + "ms");
            return list;
        } else {
            long t1 = System.currentTimeMillis();
            List<Object> list = roleDao.page(offset, pageSize);
            long t2 = System.currentTimeMillis();
            redisService.lpush(sb.toString(), list);
            log.info("从mysql取得数据,耗时:" + (t2 - t1) + "ms");
            return list;
        }
    }
}
