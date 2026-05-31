package com.jpa.fooddelivery.Utils;

import com.jpa.fooddelivery.Exceptions.InvalidFileContentException;
import com.jpa.fooddelivery.Exceptions.InvalidFilePathException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

public class Helper {

    public static Long generateRandomId() {
        return UUID.randomUUID().getMostSignificantBits();
    }

    public static String fileToPath(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileExtension = file.getOriginalFilename().substring(fileName.lastIndexOf("."));
        if (!(fileExtension.equals(".jpg") || fileExtension.equals(".jpeg") || fileExtension.equals(".png") ||  fileExtension.equals(".gif"))) {
            throw new InvalidFilePathException("Invalid file extension only jpg, jpeg, png and gif are allowed.");
        }
        String contentType = file.getContentType();
        if (!(contentType.equals("image/jpeg") || contentType.equals("image/jpg") || contentType.equals("image/gif") || contentType.equals("image/png"))) {
            throw new InvalidFileContentException("Invalid file content");
        }
        return new Date().getTime() + fileName;
    }
}
