package com.tomcai.cloud.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface RedisService {
    boolean hasKey(String key);

    List<Object> list(String key);

    void set(String key, Object value);

    String get(String key);

    Long lpush(String key, List<Object> list);

    boolean expire(String key, Long time, TimeUnit timeUnit);
}
