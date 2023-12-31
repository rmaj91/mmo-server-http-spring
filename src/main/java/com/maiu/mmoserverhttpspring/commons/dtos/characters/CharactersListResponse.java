package com.maiu.mmoserverhttpspring.commons.dtos.characters;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharactersListResponse {
    private List<CharacterResponse> characters;
}
