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
public class FileUtils {
    public static final String USER_PR = "users/";
    public static final String PUBLIC_PR = "public/";
    private static Path UPLOAD_PATH;

    public FileUtils(@Value("${file.upload-path}") String path) {
        UPLOAD_PATH = Paths.get(path).normalize();
        Path publicPath = Paths.get(path).resolve(PUBLIC_PR).normalize();
        if(!Files.exists(publicPath)) {
            try {
                Files.createDirectories(publicPath);
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
        return storeFile(file, fileName, storePath);
    }

    public static String storeFile(MultipartFile file, String fileName, Path storePath) {
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)

            Path targetLocation = UPLOAD_PATH.resolve(storePath).resolve(fileName).normalize();
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String relative = storePath.resolve(fileName).toString().replaceAll("\\\\", "/");
            log.info(relative);
            return relative;
        } catch (IOException ex) {
            throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     * 加载文件
     * @param path 文件相对路径
     * @return 文件
     */
    public static Resource loadFileAsResource(String path) {
        try {
            // test value
            Path filePath = UPLOAD_PATH.resolve(path).normalize();

            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileException("File not found " + path);
            }
        } catch (MalformedURLException ex) {
            throw new FileException("File not found " + path, ex);
        }
    }

    public static Path getUserPath(String id) {
        Path path = UPLOAD_PATH.resolve(USER_PR).resolve(id);
        if(!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                throw new FileException("Could not create the directory where the uploaded files will be stored.", e);
            }
        }
        return Paths.get(USER_PR).resolve(id);
    }
}

