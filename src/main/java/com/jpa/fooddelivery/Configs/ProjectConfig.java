package com.jpa.fooddelivery.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig {

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user = User.builder().username("admin").password("{noop}admin").build();
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user);
//        return inMemoryUserDetailsManager;
//    }
}
