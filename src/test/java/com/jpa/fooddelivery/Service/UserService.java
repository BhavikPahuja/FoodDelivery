package com.jpa.fooddelivery.Service;

import com.jpa.fooddelivery.Entities.*;
import com.jpa.fooddelivery.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class UserService {

    @Autowired
    private com.jpa.fooddelivery.Services.UserService userService;
    @Autowired
    private UserRepository userRepository;


}
