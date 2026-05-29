package com.jpa.fooddelivery.Controllers;

import com.jpa.fooddelivery.Entities.Address;
import com.jpa.fooddelivery.Entities.Restaurant;
import com.jpa.fooddelivery.Payloads.Requests.RestaurantRequestDto;
import com.jpa.fooddelivery.Payloads.Responses.RestaurantResponseDto;
import com.jpa.fooddelivery.Services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurentController {

    private final RestaurantService  restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponseDto> createRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto) {
        RestaurantResponseDto restaurantResponseDto = restaurantService.createRestaurant(restaurantRequestDto);
        return new ResponseEntity<>(restaurantResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantResponseDto>> findAllRestaurants(
        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
        @RequestParam(value = "size", required = false, defaultValue = "10") int size,
        @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
        @RequestParam(value = "sortDirection", required = false, defaultValue = "desc") String sortDirection
    ) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(restaurantService.findAllRestaurants(pageable), HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable("restaurantId") Long id) {
        return new ResponseEntity<>(restaurantService.findRestaurantById(id), HttpStatus.OK);
    }

    @GetMapping("/open")
    public ResponseEntity<Page<RestaurantResponseDto>> findAllOpenedRestaurants(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDirection", required = false, defaultValue = "desc") String sortDirection,
            @RequestParam(value = "flag", required = false, defaultValue = "true") boolean flag
    ) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(restaurantService.findAllOpenedRestaurants(flag, pageable), HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponseDto> updateRestaurant(@RequestBody RestaurantRequestDto restaurantRequestDto, @PathVariable("restaurantId") Long id) {
        return new ResponseEntity<>(restaurantService.updateRestaurant(restaurantRequestDto, id), HttpStatus.OK);
    }
}
