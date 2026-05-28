package com.jpa.fooddelivery.Payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 20, message = "Name must be between 3 to 20 chars")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?`~])[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?`~]{3,20}$", message = "Name must contains atleast 1 UpperCase Character, 1 Digit and 1 Special Character")
    private String name;

    @Min(value = 18, message = "Minimum required age is 18")
    @Max(value = 99, message = "Maximum supported age is 99")
    private int age;

    @Email(message = "Invalid E-mail address")
    private String email;

    private String gender;
}
