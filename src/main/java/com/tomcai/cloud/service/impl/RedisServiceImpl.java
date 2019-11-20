package com.tomcai.cloud.service.impl;

import com.tomcai.cloud.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public List<Object> list(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public Long lpush(String key, List<Object> list) {
        return redisTemplate.opsForList().leftPushAll(key, list);
    }

    @Override
    public boolean expire(String key, Long time, TimeUnit timeUnit) {
        return redisTemplate.expire(key, time, timeUnit);
    }
}
