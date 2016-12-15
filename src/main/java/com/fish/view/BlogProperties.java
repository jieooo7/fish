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