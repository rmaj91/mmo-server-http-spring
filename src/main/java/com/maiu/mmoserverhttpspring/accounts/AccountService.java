package com.maiu.mmoserverhttpspring.accounts;

import com.maiu.mmoserverhttpspring.commons.dtos.accounts.AccountCreateRequest;
import com.maiu.mmoserverhttpspring.commons.dtos.accounts.AccountCreatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountsRepository accountsRepository;

    public AccountCreatedResponse createAccount(AccountCreateRequest request) {
        if (accountsRepository.existsByLogin(request.getLogin())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        AccountEntity entity = new AccountEntity();
        entity.setLogin(request.getLogin().toLowerCase());//todo remove if test json
        entity.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        AccountEntity saved = accountsRepository.save(entity);

        return new AccountCreatedResponse(saved.getLogin());
    }
}
