package com.jpa.fooddelivery.Payloads;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponseDto {

    private String message;
    private HttpStatus status;
}
