package com.jpa.fooddelivery.Services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jpa.fooddelivery.Payloads.Requests.UploadRequestDto;
import com.jpa.fooddelivery.Payloads.Responses.UploadResponseDto;
import com.jpa.fooddelivery.Services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryStorageService implements UploadService {

    private final Cloudinary cloudinary;

    @Value("${cloudinary.upload.default-folder}")
    private String defaultFolder;

    @Value("${cloudinary.upload.allowed-formats}")
    private String allowedFormats;

    @Override
    public UploadResponseDto upload(MultipartFile file, UploadRequestDto uploadRequestDto, Long entityId) throws IOException {
        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                    "folder", uploadRequestDto.getFolder(),
                        "resource_type", uploadRequestDto.getResourceType(),
                        "overwrite", uploadRequestDto.isOverwrite(),
                        "public_id", String.valueOf(entityId)
                )
        );
        return UploadResponseDto.builder()
                .publicId((String) result.get("public_id"))
                .secureUrl((String) result.get("url"))
                .build();
    }

    @Override
    public void delete(Long entityId) throws IOException {
        cloudinary.uploader().destroy(String.valueOf(entityId), ObjectUtils.emptyMap());
    }

    @Override
    public UploadResponseDto uploadRestaurantBanner(MultipartFile file, Long entityId) throws IOException {
        return upload(file,
                UploadRequestDto.builder()
                        .folder(defaultFolder + "/Restaurants/Banner/")
                        .build(),
                entityId);
    }
}
