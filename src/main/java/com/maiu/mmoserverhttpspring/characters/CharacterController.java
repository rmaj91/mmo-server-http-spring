package com.maiu.mmoserverhttpspring.characters;

import com.maiu.mmoserverhttpspring.commons.dtos.characters.CharactersListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.maiu.mmoserverhttpspring.config.Config.HTTP_CHARACTERS_PREFIX;

@Slf4j
@RestController
@RequestMapping(HTTP_CHARACTERS_PREFIX)
@RequiredArgsConstructor
public class CharacterController {

    @GetMapping
    ResponseEntity<CharactersListResponse> getCharacters() {
        CharactersListResponse response = new CharactersListResponse();
        response.setCharacters(List.of("Character_1", "Character_2"));
        return ResponseEntity.ok(response);
    }
}
