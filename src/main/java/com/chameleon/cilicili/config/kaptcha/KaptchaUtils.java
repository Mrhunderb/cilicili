package com.chameleon.cilicili.config.kaptcha;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chameleon.cilicili.config.redis.RedisUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@Component
public class KaptchaUtils {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    private String key = "captcha:";

    @Autowired RedisUtils redisUtils;

    public DefaultKaptcha getDefaultKaptcha() {
        return defaultKaptcha;
    }

    public String createText() {
        return defaultKaptcha.createText();
    }

    public byte[] createImage(String text) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(defaultKaptcha.createImage(text), "jpg", outputStream);
        return outputStream.toByteArray();
    }

    public String createUUID() {
        return UUID.randomUUID().toString();
    }

    public Map<String, Object> createCaptcha() throws IOException {
        Map<String, Object> map = new HashMap<>();
        String text = createText();
        map.put("image", createImage(text));
        map.put("id", createUUID().toString());
        redisUtils.set(key+map.get("id").toString(), text, 60);
        return map;
    }
    
}
