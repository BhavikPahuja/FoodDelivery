package com.jpa.fooddelivery.Services;

import com.jpa.fooddelivery.Payloads.Requests.UserRequestDto;
import com.jpa.fooddelivery.Payloads.Responses.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto updateUser(UserRequestDto userRequestDto, Long id);
    UserResponseDto findUserById(Long id);
    Page<UserResponseDto> findAllUsers(Pageable pageable);
    Page<UserResponseDto> findAllUsersByName(String name, Pageable pageable);
    UserResponseDto findUserByEmail(String email);
    void deleteUserById(Long id);
}
