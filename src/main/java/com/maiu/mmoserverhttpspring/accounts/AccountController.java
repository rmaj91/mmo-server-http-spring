package com.maiu.mmoserverhttpspring.accounts;

import com.maiu.mmoserverhttpspring.commons.dtos.accounts.AccountCreateRequest;
import com.maiu.mmoserverhttpspring.commons.dtos.accounts.AccountCreatedResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.maiu.mmoserverhttpspring.config.Config.HTTP_ACCOUNTS_PREFIX;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(HTTP_ACCOUNTS_PREFIX)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountCreatedResponse> create(@Valid @RequestBody AccountCreateRequest request) {
        AccountCreatedResponse created = accountService.createAccount(request);
        return ResponseEntity.status(CREATED).body(created);
    }
}
