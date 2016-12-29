package com.fish.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by thy on 16-11-7.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebAppConfig.class);
    }


    /**
     * 配置拦截器
     * @author lance
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/api/**");
    }

    /**
     * spring boot 定时任务
     */
//    @Scheduled(cron="0 0 22 * * ?")
//    public void reportCurrentTime() {
//        crawler.getBlogList(1);
//    }
}