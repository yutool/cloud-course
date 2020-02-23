package com.anko.coursems.common.utils;

import com.anko.coursems.common.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadUtils {
    public static final String USER_AVATAR_PR = "users/upload_avatars/";
    public static final String USER_RESOURCE_PR = "users/upload_resource/";
    public static final String DOWN_LINK_PR = "download/";
    public static Path STORE_AVATAR;
    public static Path STORE_RESOURCE;

    public FileUploadUtils(@Value("${file.upload-path}") String path) {
        STORE_AVATAR = Paths.get(path).resolve(USER_AVATAR_PR).normalize();
        STORE_RESOURCE = Paths.get(path).resolve(USER_RESOURCE_PR).normalize();
        if(!Files.exists(STORE_AVATAR)) {
            try {
                Files.createDirectories(STORE_AVATAR);
            } catch (Exception e) {
                throw new FileException("Could not create the directory where the uploaded files will be stored.", e);
            }
        }
        if(!Files.exists(STORE_RESOURCE)) {
            try {
                Files.createDirectories(STORE_RESOURCE);
            } catch (Exception e) {
                throw new FileException("Could not create the directory where the uploaded files will be stored.", e);
            }
        }
    }

    /**
     * 存储文件到系统
     * @param file 文件
     * @return 文件名
     */
    public static String storeFile(MultipartFile file, Path storePath) {
        // random before '.x' file name
        String oldNamePrefix = file.getOriginalFilename();
        if(oldNamePrefix.contains(".")) {
            oldNamePrefix = oldNamePrefix.substring(0, oldNamePrefix.lastIndexOf("."));
        }
        String fileName = file.getOriginalFilename().replace(oldNamePrefix, UUID.randomUUID().toString());
        log.info(fileName);
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)

            Path targetLocation = storePath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     * 加载文件
     * @param fileName
     * @return 文件
     */
    public static Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = STORE_RESOURCE.resolve(fileName).normalize();

            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + fileName, ex);
        }
    }
}

