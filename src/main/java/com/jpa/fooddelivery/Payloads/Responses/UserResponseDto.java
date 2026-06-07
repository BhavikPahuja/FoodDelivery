package com.jpa.fooddelivery.Payloads.Responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jpa.fooddelivery.Entities.Authorities;
import com.jpa.fooddelivery.Payloads.AddressDto;
import com.jpa.fooddelivery.Payloads.AuthoritiesDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private AddressDto address;
    private String phoneNumber;
    private boolean available;

    private List<AuthoritiesDto> authorities;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy, hh-mm-ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy, hh-mm-ss")
    private LocalDateTime updatedAt;
}
