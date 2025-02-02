package com.chameleon.cilicili.component;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chameleon.cilicili.model.dto.UserInfoDto;
import com.chameleon.cilicili.model.entity.UserInfo;

import jakarta.servlet.http.Cookie;

@Component
public class CookieComponent {

    private static final String COOKIE_NAME = "cilicili";

    private static final String COOKIE_PATH = "/";

    private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 7;

    private static final String REDIS_KEY = "cookie:";

    @Autowired
    private final RedisComponent redisComponent;

    public CookieComponent(RedisComponent redisComponent) {
        this.redisComponent = redisComponent;
    }

    public Cookie createCookie(UserInfoDto userInfoDto) {
        String value = UUID.randomUUID().toString();
        Cookie cookie = new Cookie(COOKIE_NAME, value);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        redisComponent.set(REDIS_KEY+value, userInfoDto);
        return cookie;
    }
    
}
