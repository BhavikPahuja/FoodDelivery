package com.jpa.fooddelivery.Services.impl;

import com.jpa.fooddelivery.Entities.Authorities;
import com.jpa.fooddelivery.Entities.User;
import com.jpa.fooddelivery.Exceptions.ResourceNotFoundException;
import com.jpa.fooddelivery.Mappers.UserMapper;
import com.jpa.fooddelivery.Payloads.Requests.UserRequestDto;
import com.jpa.fooddelivery.Payloads.Responses.UserResponseDto;
import com.jpa.fooddelivery.Repositories.AuthoritiesRepository;
import com.jpa.fooddelivery.Repositories.UserRepository;
import com.jpa.fooddelivery.Services.UserService;
import com.jpa.fooddelivery.Utils.RoleConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthoritiesRepository authoritiesRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userMapper.toUser(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Authorities authority = authoritiesRepository.findByAuthority(RoleConstants.getROLE_ADMIN());
        if (authority != null) {
            user.getAuthorities().add(authority);
        }
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto, Long id) {
        User originalUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(  "User Not found with id: " + id));
        userMapper.updateUserFromDto(userRequestDto, originalUser);
        User savedUser = userRepository.save(originalUser);
        return userMapper.toUserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        User  user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not found with id: " + id));
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public Page<UserResponseDto> findAllUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        return usersPage.map(userMapper::toUserResponseDto);
    }

    @Override
    public Page<UserResponseDto> findAllUsersByName(String name, Pageable pageable) {
        Page<User> usersPage = userRepository.findByNameContainingIgnoreCase(name, pageable);
        return usersPage.map(userMapper::toUserResponseDto);
    }

    @Override
    public UserResponseDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return userMapper.toUserResponseDto(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
