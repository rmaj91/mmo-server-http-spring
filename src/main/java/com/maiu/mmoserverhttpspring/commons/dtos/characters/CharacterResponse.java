package com.maiu.mmoserverhttpspring.commons.dtos.characters;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterResponse {
    private String id;
    private String name;
}
