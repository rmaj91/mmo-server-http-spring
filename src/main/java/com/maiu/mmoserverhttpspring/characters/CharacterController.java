package com.maiu.mmoserverhttpspring.characters;

import com.maiu.mmoserverhttpspring.commons.dtos.characters.CharacterCreationRequest;
import com.maiu.mmoserverhttpspring.commons.dtos.characters.CharacterLoggingInInfoResponse;
import com.maiu.mmoserverhttpspring.commons.dtos.characters.CharacterResponse;
import com.maiu.mmoserverhttpspring.commons.dtos.characters.CharactersListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.maiu.mmoserverhttpspring.config.Config.HTTP_CHARACTERS_PREFIX;

@Slf4j
@RestController
@RequestMapping(HTTP_CHARACTERS_PREFIX)
@RequiredArgsConstructor
public class CharacterController {

    private final CharactersService charactersService;

    @GetMapping
    ResponseEntity<CharactersListResponse> getCharacters() {
        UUID accountId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(charactersService.getCharacters(accountId));
    }

    @PostMapping
    ResponseEntity<CharacterResponse> create(@Valid @RequestBody CharacterCreationRequest request) {
        UUID accountId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CharacterResponse character = charactersService.createCharacter(accountId, request);
        return ResponseEntity.ok(character);
    }

    @GetMapping("/{characterId}/game-data")
    ResponseEntity<CharacterLoggingInInfoResponse> getCharacterGameData(@PathVariable("characterId") String characterId) {
        UUID accountId = (UUID) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(charactersService.getCharacterInfo(accountId, UUID.fromString(characterId)));
    }
}
