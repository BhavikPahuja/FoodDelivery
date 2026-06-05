package com.jpa.fooddelivery.Payloads.Responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtResponse {

    String token;
}
