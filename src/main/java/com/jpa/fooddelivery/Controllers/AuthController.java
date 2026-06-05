package com.jpa.fooddelivery.Controllers;

import com.jpa.fooddelivery.Payloads.Requests.LoginRequest;
import com.jpa.fooddelivery.Payloads.Responses.JwtResponse;
import com.jpa.fooddelivery.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                );
        authenticationManager.authenticate(authenticationToken);

        String jwtToken = jwtService.generateToken(loginRequest.email());
        return ResponseEntity.ok(JwtResponse.builder().token(jwtToken).build());
    }

}
