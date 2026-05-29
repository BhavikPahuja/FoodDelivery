package com.jpa.fooddelivery.Payloads.Responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jpa.fooddelivery.Payloads.AddressDto;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponseDto {

    private Long id;

    private String name;

    @JsonIgnore
    private String banner;

    private String description;

    private AddressDto address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh-mm-ss")
    private LocalTime openingTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh-mm-ss")
    private LocalTime closingTime;

    private UserResponseDto createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy, hh-mm-ss")
    private LocalDateTime  createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy, hh-mm-ss")
    private LocalDateTime updatedAt;

    @JsonProperty
    public String imageURI() {
        return "https://localhost:8080/images/" + banner;
    }
}
