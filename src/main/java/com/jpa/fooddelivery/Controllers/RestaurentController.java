package com.jpa.fooddelivery.Controllers;

import com.jpa.fooddelivery.Entities.Address;
import com.jpa.fooddelivery.Entities.Restaurant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/restauarents")
public class RestaurentController {

    @RequestMapping("/get_restauarent")
    public Restaurant getRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(UUID.randomUUID().getMostSignificantBits());
        restaurant.setName("Restaurant");
        restaurant.setOpen(true);

        Address address = new Address();
        address.setZipCode("12345");
        address.setCity("Berlin");
        address.setState("Berlin");
        address.setCountry("Berlin");
        restaurant.setAddress(address);

        return restaurant;
    }
}
