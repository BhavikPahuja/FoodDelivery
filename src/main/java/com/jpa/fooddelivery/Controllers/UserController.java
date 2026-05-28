package com.jpa.fooddelivery.Controllers;

import com.jpa.fooddelivery.Entities.Address;
import com.jpa.fooddelivery.Entities.Role;
import com.jpa.fooddelivery.Entities.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping("/get_user")
    public User getUser() {
        User user = new User();
        user.setId(UUID.randomUUID().getMostSignificantBits());
        user.setPassword("123456");
        user.setRole(Role.CUSTOMER);
        user.setName("Jack");
        user.setEmail("abc@abc.abc");
        user.setAvailable(true);
        user.setPhoneNumber("7339936015");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        Address address = new Address();
        address.setZipCode("12345");
        address.setCity("Berlin");
        address.setState("Berlin");
        address.setCountry("Berlin");
        user.setAddress(address);

        return user;
    }
}
