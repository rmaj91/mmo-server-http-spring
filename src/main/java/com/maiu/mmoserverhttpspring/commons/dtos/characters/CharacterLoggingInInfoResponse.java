package com.maiu.mmoserverhttpspring.commons.dtos.characters;

import com.maiu.mmoserverhttpspring.commons.dtos.Position;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterLoggingInInfoResponse {
    private String id;
    private String name;
    private Position position;
    private String zoneId;
    //bags, talents, custom UI layout etc...
}
