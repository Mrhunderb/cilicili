package com.chameleon.cilicili.config.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {

    @Bean
    DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no"); //是否有边框 yes有 no没有
        properties.put("kaptcha.textproducer.font.color", "blue"); //验证码字体颜色
        properties.put("kaptcha.image.width", "150"); //验证码图片的宽
        properties.put("kaptcha.image.height", "40"); //验证码图片的高
        properties.put("kaptcha.textproducer.font.size", "30"); //验证码字体大小
        properties.put("kaptcha.session.key", "verifyCode"); //存储在session中值的key
        properties.put("kaptcha.textproducer.char.length", "4"); //验证码字符个数
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }

}

