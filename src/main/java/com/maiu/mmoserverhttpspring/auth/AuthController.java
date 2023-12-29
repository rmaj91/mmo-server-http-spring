package com.maiu.mmoserverhttpspring.auth;

import com.maiu.mmoserverhttpspring.commons.dtos.auth.LoginRequest;
import com.maiu.mmoserverhttpspring.commons.dtos.auth.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.maiu.mmoserverhttpspring.config.Config.HTTP_AUTH_LOGIN_RESOURCE;
import static com.maiu.mmoserverhttpspring.config.Config.HTTP_AUTH_PREFIX;

@RestController
@RequestMapping(HTTP_AUTH_PREFIX)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(HTTP_AUTH_LOGIN_RESOURCE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticate(loginRequest));
    }
}
