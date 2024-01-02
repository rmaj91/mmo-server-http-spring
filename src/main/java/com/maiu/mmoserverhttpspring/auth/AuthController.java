package com.maiu.mmoserverhttpspring.auth;

import com.maiu.mmoserverhttpspring.accounts.AccountService;
import com.maiu.mmoserverhttpspring.commons.dtos.accounts.AccountCreatedResponse;
import com.maiu.mmoserverhttpspring.commons.dtos.auth.LoginRequest;
import com.maiu.mmoserverhttpspring.commons.dtos.auth.LoginResponse;
import com.maiu.mmoserverhttpspring.guest.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.maiu.mmoserverhttpspring.config.Config.*;

@RestController
@RequestMapping(HTTP_AUTH_PREFIX)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final GuestService guestService;

    @PostMapping(HTTP_AUTH_LOGIN_RESOURCE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticate(loginRequest));
    }

    @PostMapping(HTTP_AUTH_LOGIN_AS_GUEST_RESOURCE)
    public ResponseEntity<LoginResponse> loginAsGuest() {
        AccountCreatedResponse guestAccount = guestService.createGuestAccount();
        return ResponseEntity.ok(authService.authenticateUnsecure(guestAccount.getId()));
    }

    @PostMapping(HTTP_AUTH_LOGOUT_RESOURCE)
    public ResponseEntity<?> logout() {
        UUID accountId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        authService.logout(accountId);
        return ResponseEntity.ok().build();
    }
}
