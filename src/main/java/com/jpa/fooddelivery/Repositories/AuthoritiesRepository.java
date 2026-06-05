package com.jpa.fooddelivery.Repositories;

import com.jpa.fooddelivery.Entities.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    Authorities findByAuthority(String authority);
}
