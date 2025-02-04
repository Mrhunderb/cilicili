package com.chameleon.cilicili.component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chameleon.cilicili.exception.KaptchaException;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@Component
public class KaptchaComponent {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired 
    private RedisComponent redisUtils;

    private final String KEY = "captcha:";

    private final long TIMEOUT_SECOND = 120;

    private final String FORMAT = "png";

    private final String DATA_URL_PREFIX = "data:image/"+ FORMAT +";base64,";

    public DefaultKaptcha getDefaultKaptcha() {
        return defaultKaptcha;
    }

    public String createText() {
        return defaultKaptcha.createText();
    }

    public String createImage(String text) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(defaultKaptcha.createImage(text), FORMAT, outputStream);
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            throw new KaptchaException("生成验证码图片失败");
        }
    }

    public String createUUID() {
        return UUID.randomUUID().toString();
    }

    public Map<String, Object> createCaptcha() {
        Map<String, Object> map = new HashMap<>();
        String text = createText();
        map.put("image", DATA_URL_PREFIX+createImage(text));
        map.put("id", createUUID().toString());
        redisUtils.set(KEY+map.get("id").toString(), text, TIMEOUT_SECOND);
        return map;
    }

    public void validateCaptcha(String id, String text) {
        try {
            String captcha = (String) redisUtils.get(KEY+id);
            if (captcha == null || !captcha.equals(text)) {
                throw new KaptchaException("验证码错误");
            }
        } finally {
            redisUtils.delete(KEY+id);
        }
    }
    
}
