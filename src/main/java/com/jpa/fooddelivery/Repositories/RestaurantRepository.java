package com.jpa.fooddelivery.Repositories;

import com.jpa.fooddelivery.Entities.Restaurant;
import com.jpa.fooddelivery.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Restaurant> findByOpen(Boolean open, Pageable pageable);
}
