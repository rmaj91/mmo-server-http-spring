package com.maiu.mmoserverhttpspring.characters;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharactersListResponse {
    private List<String> characters;
}
