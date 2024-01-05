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
    private final SessionRepository sessionRepository;//todo make it in memory???
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
                    .token(saved.getId().toString())
                    .isGuest(accountEntity.get().isGuestAccount())
                    .build();
            log.info("Successfully authenticated user '{}'.", loginRequest.getUsername());
        } else {
            log.error("Failed to authenticate user '{}'.", loginRequest.getUsername());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return response;
    }

    @Transactional
    public LoginResponse authenticateUnsecure(String accountId) {
        AccountEntity accountEntity = accountsRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new RuntimeException("Account not exists. id: " + accountId));

        sessionRepository.deleteAllByAccountId(accountEntity.getId());

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setAccountId(accountEntity.getId());
        SessionEntity saved = sessionRepository.save(sessionEntity);

        LoginResponse response = LoginResponse.builder()
                .username(accountEntity.getUsername())
                .token(saved.getId().toString())
                .isGuest(accountEntity.isGuestAccount())
                .build();
        log.info("Successfully authenticated user '{}'.", accountEntity.getUsername());
        return response;
    }

    public void logout(UUID accountId) {
        sessionRepository.deleteAllByAccountId(accountId);
        accountsRepository.findById(accountId)
                .map(AccountEntity::getUsername)
                .ifPresentOrElse(
                        username -> log.info("Successfully logout user '{}'.", username),
                        () -> log.warn("Account '{}' not found.", accountId));
    }
}
