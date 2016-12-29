package com.fish.storage;

import com.fish.securety.MD5;
import com.fish.util.CodeGenetate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

@Service
//@EnableConfigurationProperties(StorageProperties.class)
public class FileSystemStorageService implements StorageService {

    private Path rootLocation;


    private String name;


    public FileSystemStorageService() {
    }

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {

        this.rootLocation = Paths.get(properties.getUpload());
//        this.rootLocation = Paths.get("/home/thy/www/file/");
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            long time = new Date().getTime();
            name=MD5.getMD5(time + CodeGenetate.getInstance().create() + URLEncoder.encode(file.getOriginalFilename(),"UTF-8"));
            Files.copy(file.getInputStream(), this.rootLocation.resolve(name));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }


    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            if (!Files.exists(rootLocation)) {

                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public String getName(){

        return name;
    }

    @Override
    public void setDir(String dir) {
        this.rootLocation = Paths.get(dir);
    }

}
