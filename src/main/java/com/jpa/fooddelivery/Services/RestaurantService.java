package com.jpa.fooddelivery.Services;

import com.jpa.fooddelivery.Payloads.Requests.RestaurantRequestDto;
import com.jpa.fooddelivery.Payloads.Responses.RestaurantResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {

    RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto);
    RestaurantResponseDto updateRestaurant(RestaurantRequestDto restaurantRequestDto, Long id);
    RestaurantResponseDto findRestaurantById(Long id);
    Page<RestaurantResponseDto> findAllRestaurants(Pageable pageable);
    Page<RestaurantResponseDto> findAllRestaurantsByName(String name, Pageable pageable);
    Page<RestaurantResponseDto> findAllOpenedRestaurants(boolean flag, Pageable pageable);
    void deleteRestaurantById(Long id);
}
