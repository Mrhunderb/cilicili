package com.chameleon.cilicili.component;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chameleon.cilicili.model.dto.UserInfoDto;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        userInfoDto.setToken(value);
        userInfoDto.setExpireTime(System.currentTimeMillis() + COOKIE_MAX_AGE * 1000);
        redisComponent.set(REDIS_KEY+value, userInfoDto, COOKIE_MAX_AGE);
        return cookie;
    }

    private void deleteCookie(Cookie cookie) {
        redisComponent.delete(REDIS_KEY+cookie.getValue());
    }

    public void clearCookie(HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    deleteCookie(cookie);
                    break;
                }
            }
        }
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    
}
