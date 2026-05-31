package com.jpa.fooddelivery.Services;

import com.jpa.fooddelivery.Payloads.Requests.UploadRequestDto;
import com.jpa.fooddelivery.Payloads.Responses.UploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    UploadResponseDto upload(MultipartFile file, UploadRequestDto uploadRequestDto, Long entityId) throws IOException;
    void delete(Long entityId) throws IOException;

    UploadResponseDto uploadRestaurantBanner(MultipartFile file, Long entityId) throws IOException;
}
