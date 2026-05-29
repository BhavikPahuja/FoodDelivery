package com.jpa.fooddelivery.Mappers;

import com.jpa.fooddelivery.Entities.Restaurant;
import com.jpa.fooddelivery.Payloads.Requests.RestaurantRequestDto;
import com.jpa.fooddelivery.Payloads.Responses.RestaurantResponseDto;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        uses = {AddressMapper.class, UserMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RestaurantMapper {

    @Mapping(source = "openingTime",  target = "openingTime")
    @Mapping(source = "closingTime", target = "closingTime")
    RestaurantResponseDto toRestaurantResponseDto(Restaurant restaurant);

    @Mapping(target = "id",        ignore = true)
    @Mapping(target = "open",      ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "openingTime",  target = "openingTime")
    @Mapping(source = "closingTime", target = "closingTime")
    Restaurant toRestaurant(RestaurantRequestDto restaurantRequestDto);

    @Mapping(target = "id",        ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateRestaurantFromDto(RestaurantRequestDto dto, @MappingTarget Restaurant existingRestaurant);
}