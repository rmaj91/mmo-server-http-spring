package com.maiu.mmoserverhttpspring.commons.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Position {
    private float px;
//    private float py; //todo not supported for now
    private float pz;
}
