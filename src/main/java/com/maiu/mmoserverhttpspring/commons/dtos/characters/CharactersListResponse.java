package com.maiu.mmoserverhttpspring.commons.dtos.characters;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharactersListResponse {
    private List<String> characters;
}
