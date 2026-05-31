package com.jpa.fooddelivery.Services.impl;

import com.jpa.fooddelivery.Exceptions.InvalidFilePathException;
import com.jpa.fooddelivery.Payloads.FileData;
import com.jpa.fooddelivery.Payloads.Responses.RestaurantResponseDto;
import com.jpa.fooddelivery.Services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    private final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public FileData uploadFile(MultipartFile file, String path) throws IOException {

        if (path.isBlank()) {
            throw new InvalidFilePathException("Invalid path");
        }

        Path folderPath = Paths.get(path.substring(0, path.lastIndexOf('/')));
        logger.info("Uploading file to folder {}", folderPath.toString());

        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        Path filePath = Paths.get(path);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        String fileName = path.substring(path.lastIndexOf('/') + 1);
        return new FileData(fileName, path);
    }

    @Override
    public void deleteFile(String path) throws IOException {

        if (path.isBlank()) {
            throw new InvalidFilePathException("Invalid path");
        }

        Path filePath = Paths.get(path);
        Files.deleteIfExists(filePath);
    }

    @Override
    public Resource getFile(String path) throws IOException {

        if (path.isBlank()) {
            throw new InvalidFilePathException("Invalid path");
        }

        Path filePath = Paths.get(path);
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            throw new InvalidFilePathException("Not found Banner File at Path: " + path);
        }

        return resource;
    }
}
