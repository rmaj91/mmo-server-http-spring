package com.maiu.mmoserverhttpspring.commons.dtos.characters;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.maiu.mmoserverhttpspring.commons.dtos.Constants.*;

@Getter
@Setter
public class CharacterCreationRequest {

    @Size(min = MIN_CHARACTER_NAME_LENGTH, max = MAX_CHARACTER_NAME_LENGTH)
    @Pattern(regexp = CHARACTER_NAME_VALIDATION_REGEXP)
    private String name;

    @JsonSetter("name")
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name =  Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
        } else {
            this.name = name;
        }
    }
}
