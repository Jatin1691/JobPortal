package com.jobportal.JWT;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class AuthenticationResponse {

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    private final String token;
}
