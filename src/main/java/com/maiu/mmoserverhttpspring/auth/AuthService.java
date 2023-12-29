package com.maiu.mmoserverhttpspring.auth;

import com.maiu.mmoserverhttpspring.accounts.AccountEntity;
import com.maiu.mmoserverhttpspring.accounts.AccountsRepository;
import com.maiu.mmoserverhttpspring.commons.dtos.auth.LoginRequest;
import com.maiu.mmoserverhttpspring.commons.dtos.auth.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountsRepository accountsRepository;
    private final SessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public LoginResponse authenticate(LoginRequest loginRequest) {
        Optional<AccountEntity> accountEntity = accountsRepository.findByUsername(loginRequest.getUsername());
        boolean credentialsMatches = accountEntity
                .map(account -> passwordEncoder.matches(loginRequest.getPassword(), account.getPasswordHash()))
                .orElse(false);

        LoginResponse response;
        if (credentialsMatches) {
            //remove current session
            UUID accountId = accountEntity.get().getId();
            sessionRepository.deleteAllByAccountId(accountId);

            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setAccountId(accountId);
            SessionEntity saved = sessionRepository.save(sessionEntity);

            response = LoginResponse.builder()
                    .username(loginRequest.getUsername())
                    .session(saved.getId().toString())
                    .build();
            log.info("Successfully authenticated user '{}'.", loginRequest.getUsername());
        } else {
            log.error("Failed to authenticate username '{}'.", loginRequest.getUsername());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return response;
    }
}
