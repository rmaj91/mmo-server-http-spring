package com.maiu.mmoserverhttpspring.commons.dtos.zone;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Zone {
    private String id;
    private String name;
    private int sizeX;
    private int sizeZ;

    private List<EnvironmentElement> environmentElements;

    //meshes list coordinates to Id
    // mesh regular mesh and collision box
    // every mesh should have version to cache and invalidate in browser
    // zone should have size, coordinance for environment. id of meshes (regular and collision ones). meshed can be cached in idexDB if not too much
    // and load rest of the meshes from server. keep mesh version in zone/character info to not load not necessary
}
