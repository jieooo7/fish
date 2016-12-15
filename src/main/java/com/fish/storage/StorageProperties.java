package com.fish.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(locations="classpath:/file.properties",prefix="storage.file")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";


    public StorageProperties() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
