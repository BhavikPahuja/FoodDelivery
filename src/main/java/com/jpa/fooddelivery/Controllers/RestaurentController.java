package com.jpa.fooddelivery.Controllers;

import com.jpa.fooddelivery.Exceptions.InvalidFilePathException;
import com.jpa.fooddelivery.Payloads.Requests.RestaurantRequestDto;
import com.jpa.fooddelivery.Payloads.Responses.RestaurantResponseDto;
import com.jpa.fooddelivery.Services.FileService;
import com.jpa.fooddelivery.Services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurentController {

    private final RestaurantService  restaurantService;
    private final FileService fileService;

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

    @PostMapping("/upload-banner/{restaurantId}")
    public ResponseEntity<RestaurantResponseDto> uploadBanner(
            @PathVariable("restaurantId") Long id,
            @RequestParam("banner")MultipartFile banner
            ) throws IOException {
        RestaurantResponseDto restaurantResponseDto = restaurantService.uploadBanner(banner, id);
        return ResponseEntity.ok(restaurantResponseDto);
    }

    @GetMapping("/{restaurantId}/get-banner")
    public ResponseEntity<Resource> getBanner(@PathVariable("restaurantId") Long id) throws IOException {
        Resource banner = restaurantService.getBanner(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + banner.getFilename() + "\"")
                .body(banner);
    }
}
