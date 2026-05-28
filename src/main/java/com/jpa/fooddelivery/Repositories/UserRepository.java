package com.jpa.fooddelivery.Repositories;

import com.jpa.fooddelivery.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
