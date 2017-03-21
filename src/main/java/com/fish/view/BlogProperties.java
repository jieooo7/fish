package com.fish.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by thy on 16-12-14.
 */

@Component
@ConfigurationProperties(prefix="com.didispace.blog")
public class BlogProperties {
    private String name;
    private String title;
    // 省略getter和setter


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


//使用@PropertySource引入配置文件
//@Configuration
//@PropertySource(value = "classpath:/redis.properties")
//@EnableCaching
//public class CacheConfig extends CachingConfigurerSupport {
//    ......
//}
//使用@Value引用属性值
//@Configuration
//@PropertySource(value = "classpath:/redis.properties")
//@EnableCaching
//public class CacheConfig extends CachingConfigurerSupport {
//    @Value("${spring.redis.host}")
//    private String host;
//    @Value("${spring.redis.port}")
//    private int port;
//    @Value("${spring.redis.timeout}")
//    private int timeout;
//    ......
//}