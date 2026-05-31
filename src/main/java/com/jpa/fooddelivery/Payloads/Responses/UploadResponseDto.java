package com.jpa.fooddelivery.Payloads.Responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponseDto {
    private String publicId;
    private String secureUrl;
}
