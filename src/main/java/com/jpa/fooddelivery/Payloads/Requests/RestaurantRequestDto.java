package com.jpa.fooddelivery.Payloads.Requests;

import com.jpa.fooddelivery.Payloads.AddressDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantRequestDto {

    @NotBlank
    private String name;

    private String banner;

    private String description;

    @NotNull
    private AddressDto  address;

    @NotNull
    private LocalTime openingTime;

    @NotNull
    private LocalTime closingTime;

    private boolean open;

    private Long createdBy;
}
