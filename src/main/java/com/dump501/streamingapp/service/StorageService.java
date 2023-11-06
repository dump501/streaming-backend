package com.dump501.streamingapp.service;

import com.dump501.streamingapp.config.StorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class StorageService {
    private final Path rootLocation;

    public StorageService(StorageProperties properties){
        rootLocation = Paths.get(properties.getLocation());
    }
    public void init(){
        try{
            Files.createDirectories(rootLocation);
        } catch (IOException e){
            log.error("Cannot create root location directory", e);
        }
    }

    public String upload(MultipartFile file){
        try{
            String newName = String.valueOf(System.currentTimeMillis());
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if(file.isEmpty()){
                log.error("Failed to store empty file");
            }
            Path destinationFile = this.rootLocation
                    .resolve(Paths.get(newName + extension))
                    .normalize().toAbsolutePath();
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                return newName+extension;
            }
        } catch (Exception e){
            log.error("Error while uploading",e);
        }

        return null;
    }

    public Path load(String fileName){
        return rootLocation.resolve(fileName);
    }

    public Resource download(String fileName){
        try{
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            } else {
                log.error("Cannot read the file " + fileName);
            }
        } catch (IOException e){
            log.error("Cannot read file", e);
        }
        return null;
    }
}
