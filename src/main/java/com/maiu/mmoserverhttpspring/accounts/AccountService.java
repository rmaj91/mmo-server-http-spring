package com.maiu.mmoserverhttpspring.accounts;

import com.maiu.mmoserverhttpspring.commons.dtos.accounts.AccountCreateRequest;
import com.maiu.mmoserverhttpspring.commons.dtos.accounts.AccountCreatedResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountsRepository accountsRepository;

    public AccountCreatedResponse createAccount(AccountCreateRequest request) {
        if (accountsRepository.existsByUsername(request.getUsername())) {
            log.error("Account: '{}' already exists", request.getUsername());
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        AccountEntity entity = new AccountEntity();
        entity.setUsername(request.getUsername().toLowerCase());//todo remove if test json
        entity.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        AccountEntity saved = accountsRepository.save(entity);
        log.error("Account: '{}' successfully exists", request.getUsername());

        return new AccountCreatedResponse(saved.getUsername());
    }
}
