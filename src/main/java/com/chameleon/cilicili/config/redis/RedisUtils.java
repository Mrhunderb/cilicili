package com.chameleon.cilicili.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String prefix = "cilicili:";

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(prefix+key);
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(String key) {
        redisTemplate.delete(prefix+key);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(prefix+key);
    }

    public void expire(String key, long timeout) {
        redisTemplate.expire(prefix+key, timeout, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void expire(String key, long timeout, java.util.concurrent.TimeUnit timeUnit) {
        redisTemplate.expire(prefix+key, timeout, timeUnit);
    }

    public void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(prefix+key, value, timeout, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void set(String key, Object value, long timeout, java.util.concurrent.TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(prefix+key, value, timeout, timeUnit);
    }

}
