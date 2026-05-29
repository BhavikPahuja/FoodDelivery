package com.jpa.fooddelivery.Controllers;

import com.jpa.fooddelivery.Payloads.Requests.UserRequestDto;
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
    public String signUp(@Valid @RequestBody UserRequestDto userRequestDTO) {

        logger.info("userName : {}",  userRequestDTO.getName());
        logger.info("email : {}",  userRequestDTO.getEmail());
        logger.info("password : {}",  userRequestDTO.getPassword());

        return "success";
    }

}
