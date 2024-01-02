package com.maiu.mmoserverhttpspring.guest;

import com.maiu.mmoserverhttpspring.accounts.AccountEntity;
import com.maiu.mmoserverhttpspring.accounts.AccountService;
import com.maiu.mmoserverhttpspring.characters.CharacterEntity;
import com.maiu.mmoserverhttpspring.characters.CharactersService;
import com.maiu.mmoserverhttpspring.commons.dtos.accounts.AccountCreateRequest;
import com.maiu.mmoserverhttpspring.commons.dtos.accounts.AccountCreatedResponse;
import com.maiu.mmoserverhttpspring.commons.dtos.characters.CharacterCreationRequest;
import com.maiu.mmoserverhttpspring.commons.dtos.characters.CharacterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.maiu.mmoserverhttpspring.commons.dtos.Constants.MAX_CHARACTER_NAME_LENGTH;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final AccountService accountService;
    private final CharactersService charactersService;
    private final RandomNameGenerator randomNameGenerator;

    @Transactional
    public AccountCreatedResponse createGuestAccount() {
        AccountCreatedResponse account = accountService.createGuestAccount();
        CharacterCreationRequest characterCreationRequest = new CharacterCreationRequest();
        characterCreationRequest.setName(randomNameGenerator.generateRandomCharacterName());
        CharacterResponse character = charactersService.createCharacter(UUID.fromString(account.getId()), characterCreationRequest);

        return account;
    }

}
