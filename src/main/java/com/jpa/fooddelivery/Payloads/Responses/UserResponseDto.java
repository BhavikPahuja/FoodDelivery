package com.jpa.fooddelivery.Payloads.Responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jpa.fooddelivery.Entities.Role;
import com.jpa.fooddelivery.Payloads.AddressDto;
import lombok.*;

import java.time.LocalDateTime;

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
    private Role role;
    private boolean available;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy, hh-mm-ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy, hh-mm-ss")
    private LocalDateTime updatedAt;
}
