package com.jpa.fooddelivery.Controllers;

import com.jpa.fooddelivery.Payloads.UserDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/signup")
    public String signUp(@Valid @RequestBody UserDTO userDTO) {

        logger.info("userName : {}",  userDTO.getName());
        logger.info("age : {}",  userDTO.getAge());
        logger.info("email : {}",  userDTO.getEmail());

        return "success";
    }

}
