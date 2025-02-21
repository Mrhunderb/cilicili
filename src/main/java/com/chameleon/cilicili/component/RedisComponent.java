package com.chameleon.cilicili.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisComponent {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String PREFIX = "cilicili:";

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(PREFIX+key, value);
    }

    public Object get(String key) {
        try {
            return redisTemplate.opsForValue().get(PREFIX+key);
        } catch (Exception e) {
            return null;
        }
    }

    public void delete(String key) {
        redisTemplate.delete(PREFIX+key);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(PREFIX+key);
    }

    public void expire(String key, long timeout) {
        redisTemplate.expire(PREFIX+key, timeout, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void expire(String key, long timeout, java.util.concurrent.TimeUnit timeUnit) {
        redisTemplate.expire(PREFIX+key, timeout, timeUnit);
    }

    public void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(PREFIX+key, value, timeout, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void set(String key, Object value, long timeout, java.util.concurrent.TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(PREFIX+key, value, timeout, timeUnit);
    }

}
