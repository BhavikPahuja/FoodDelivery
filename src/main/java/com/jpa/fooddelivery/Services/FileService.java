package com.jpa.fooddelivery.Services;

import com.jpa.fooddelivery.Payloads.FileData;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileData uploadFile(MultipartFile file, String path) throws IOException;
    void deleteFile(String path) throws IOException;
    Resource getFile(String path) throws IOException;
}
