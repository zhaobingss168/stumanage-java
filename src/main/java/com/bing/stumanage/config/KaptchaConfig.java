package com.bing.stumanage.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 验证码配置
 * @Author zhaobing
 * @Date 2020/1/25 11:02
 **/
@Configuration
public class KaptchaConfig {

    /**
     * 配置属性参考https://blog.csdn.net/elephantboy/article/details/52795309
     * @return
     */
    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.length", "4");
        properties.put("kaptcha.image.height", "60");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
