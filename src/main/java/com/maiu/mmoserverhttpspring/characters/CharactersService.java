package com.maiu.mmoserverhttpspring.characters;

import com.maiu.mmoserverhttpspring.commons.dtos.Position;
import com.maiu.mmoserverhttpspring.commons.dtos.characters.CharacterCreationRequest;
import com.maiu.mmoserverhttpspring.commons.dtos.characters.CharacterLoggingInInfoResponse;
import com.maiu.mmoserverhttpspring.commons.dtos.characters.CharacterResponse;
import com.maiu.mmoserverhttpspring.commons.dtos.characters.CharactersListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharactersService {
    
    private final CharactersRepository charactersRepository;
    
    public CharactersListResponse getCharacters(UUID accountId) {
        List<CharacterResponse> characters = charactersRepository.findAllByAccountIdOrderByCreationDateAsc(accountId)
                .stream()
                .map(characterEntity -> CharacterResponse.builder()
                        .id(characterEntity.getId().toString())
                        .name(characterEntity.getName())
                        .build()
                )
                .toList();
        return CharactersListResponse.builder().characters(characters).build();
    }

    public CharacterResponse createCharacter(UUID accountId, CharacterCreationRequest request) {
        if (charactersRepository.existsByName(request.getName())) {
            log.warn("Character with name: '{}' already exists,", request.getName());
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        CharacterEntity entity = new CharacterEntity();
        entity.setName(request.getName());
        entity.setAccountId(accountId);
        CharacterEntity saved = charactersRepository.save(entity);

        log.info("Character with name '{}' successfully created.", saved.getName());
        return CharacterResponse.builder()
                .id(saved.getId().toString())
                .name(saved.getName())
                .build();
    }

    public CharacterLoggingInInfoResponse getCharacterInfo(UUID accountId, UUID characterId) {
        return charactersRepository.findByIdAndAccountId(characterId, accountId)
                .map(entity -> CharacterLoggingInInfoResponse.builder()
                        .id(entity.getId().toString())
                        .name(entity.getName())
                        .position(Position.builder().px(0).pz(0).build()) //todo vector etc... or single endpoint for position?? //default for now
                        .zoneId("zoneId")
                        .build()
                )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public boolean ifExists(UUID accountId, UUID characterId) {
        return charactersRepository.existsByIdAndAccountId(characterId, accountId);
    }
}
