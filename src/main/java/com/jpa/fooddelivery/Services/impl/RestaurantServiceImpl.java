package com.jpa.fooddelivery.Services.impl;

import com.jpa.fooddelivery.Entities.Restaurant;
import com.jpa.fooddelivery.Exceptions.ResourceNotFoundException;
import com.jpa.fooddelivery.Mappers.RestaurantMapper;
import com.jpa.fooddelivery.Payloads.FileData;
import com.jpa.fooddelivery.Payloads.Requests.RestaurantRequestDto;
import com.jpa.fooddelivery.Payloads.Requests.UploadRequestDto;
import com.jpa.fooddelivery.Payloads.Responses.RestaurantResponseDto;
import com.jpa.fooddelivery.Payloads.Responses.UploadResponseDto;
import com.jpa.fooddelivery.Repositories.RestaurantRepository;
import com.jpa.fooddelivery.Services.FileService;
import com.jpa.fooddelivery.Services.RestaurantService;
import com.jpa.fooddelivery.Services.UploadService;
import com.jpa.fooddelivery.Utils.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantMapper restaurantMapper;
    private final RestaurantRepository restaurantRepository;
    private final FileService fileService;
    private final UploadService uploadService;

    @Value("${restaurant.banner.file.path}")
    private String bannerFolderPath;

    @Value("${restaurant.banner.file.path}")
    private String bannerPath;

    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantRequestDto);
        restaurantRepository.save(restaurant);
        return restaurantMapper.toRestaurantResponseDto(restaurant);
    }

    @Override
    public RestaurantResponseDto updateRestaurant(RestaurantRequestDto restaurantRequestDto, Long id) {
        Restaurant originalRestaurant = restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
        restaurantMapper.updateRestaurantFromDto(restaurantRequestDto, originalRestaurant);
        restaurantRepository.save(originalRestaurant);
        return restaurantMapper.toRestaurantResponseDto(originalRestaurant);
    }

    @Override
    public RestaurantResponseDto findRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
        return restaurantMapper.toRestaurantResponseDto(restaurant);
    }

    @Override
    public Page<RestaurantResponseDto> findAllRestaurants(Pageable pageable) {
        Page<Restaurant> restaurantsPage = restaurantRepository.findAll(pageable);
        return restaurantsPage.map(restaurantMapper::toRestaurantResponseDto);
    }

    @Override
    public Page<RestaurantResponseDto> findAllRestaurantsByName(String name, Pageable pageable) {
        Page<Restaurant> restaurantPage = restaurantRepository.findByNameContainingIgnoreCase(name, pageable);
        return restaurantPage.map(restaurantMapper::toRestaurantResponseDto);
    }

    @Override
    public Page<RestaurantResponseDto> findAllOpenedRestaurants(boolean flag, Pageable pageable) {
        Page<Restaurant> restaurantsPage = restaurantRepository.findByOpen(flag, pageable);
        return restaurantsPage.map(restaurantMapper::toRestaurantResponseDto);
    }

    @Override
    public void deleteRestaurantById(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public RestaurantResponseDto uploadBanner(MultipartFile banner, Long id) throws IOException {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
//        FileData fileData = fileService.uploadFile(banner, bannerPath + Helper.fileToPath(banner));
//        restaurant.setBanner(fileData.fileName());
//        restaurantRepository.save(restaurant);
//        return restaurantMapper.toRestaurantResponseDto(restaurant);
        UploadResponseDto response = uploadService.uploadRestaurantBanner(banner, restaurant.getId());
        restaurant.setBanner(response.getSecureUrl());
        restaurantRepository.save(restaurant);
        return restaurantMapper.toRestaurantResponseDto(restaurant);
    }

    @Override
    public Resource getBanner(Long id) throws IOException {
        RestaurantResponseDto restaurantResponseDto = findRestaurantById(id);
        String fullBannerPath = bannerFolderPath + restaurantResponseDto.getBanner();
        return fileService.getFile(fullBannerPath);
    }
}
