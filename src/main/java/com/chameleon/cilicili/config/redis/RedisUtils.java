package com.chameleon.cilicili.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void expire(String key, long timeout, java.util.concurrent.TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }

    public void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void set(String key, Object value, long timeout, java.util.concurrent.TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public String generateID() {
        Long id = redisTemplate.opsForValue().increment("user:id");
        return String.valueOf(id);
    }

}
