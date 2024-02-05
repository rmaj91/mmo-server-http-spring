package com.maiu.mmoserverhttpspring.zones;

import com.maiu.mmoserverhttpspring.commons.dtos.zone.EnvironmentElement;
import com.maiu.mmoserverhttpspring.commons.dtos.zone.Zone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.maiu.mmoserverhttpspring.config.Config.HTTP_ZONES_PREFIX;

@Slf4j
@RestController
@RequestMapping(HTTP_ZONES_PREFIX)
public class ZoneController {

    @GetMapping("/{zoneId}")
    public ResponseEntity<Zone> getZone(@PathVariable("zoneId") String zoneId) {
        log.info("Getting zone by id: {}", zoneId);
        Zone zone = Zone.builder()
                .id(zoneId)
                .name("BorkiSands")
                .sizeX(100)
                .sizeZ(100)
                .environmentElements(new ArrayList<EnvironmentElement>(List.of(
//                        List.of(
                                EnvironmentElement.builder()
                                        .type("block")
                                        .name("block_1_no_rotation")
                                        .x(17)
                                        .z(13)
                                        .y(0)
                                        .lengthX(7)
                                        .lengthZ(2.5)
                                        .lengthY(3)
                                        .orientationAngle(0)
                                        .collisions(true)
                                        .build(),
                                EnvironmentElement.builder()
                                        .type("block")
                                        .name("block_2_rotation")
                                        .x(33)
                                        .z(22)
                                        .y(0)
                                        .lengthX(13)
                                        .lengthZ(5.5)
                                        .lengthY(1)
                                        .orientationAngle(40)
                                        .collisions(true)
                                        .build(),
                                EnvironmentElement.builder()
                                        .type("block")
                                        .name("block_3_no_rotation")
                                        .x(29)
                                        .z(37)
                                        .y(0)
                                        .lengthX(11)
                                        .lengthZ(4.5)
                                        .lengthY(1.75)
                                        .orientationAngle(17)
                                        .collisions(false)
                                        .build(),
                                EnvironmentElement.builder()
                                        .type("block")
                                        .name("block_4_no_rotation")
                                        .x(5)
                                        .z(25)
                                        .y(0)
                                        .lengthX(1)
                                        .lengthZ(25)
                                        .lengthY(1)
                                        .orientationAngle(0)
                                        .collisions(true)
                                        .build(),
                                buildMesh("tree_1", "Tree1.glb", 17, 17, 0, true),
                                buildMesh("tree_2", "Tree1.glb", 55, 17, 222, true),
                                buildMesh("tree_3", "Tree4.glb", 22, 17, 22, true),
                                buildMesh("tree_4", "Tree4.glb", 12, 77, 66, true),
                                buildMesh("tree_4", "Tree4.glb", 1, 33, 66, true),
                                buildMesh("tree_4", "Tree4.glb", 15, 44, 66, true),
                                buildMesh("tree_4", "Tree4.glb", 37, 24, 66, true),
                                buildMesh("tree_5", "Tree1.glb", 2, 18, 15, true),
                                buildMesh("rock_1", "Rock1.glb", 65, 18, 15, true),
                                buildMesh("rock_2", "Rock1.glb", 50, 18, 45, true),
                                buildMesh("rock_3", "Rock2.glb", 44, 82, 233, true),
                                buildMesh("rock_4", "Rock2.glb", 88, 18, 200, true),
                                buildMesh("rock_5", "Rock2.glb", 66, 66, 0, true),
                                buildMesh("grass_1", "Grass1.glb", 44, 82, 233, false),
                                buildMesh("grass_2", "Grass2.glb", 88, 18, 200, false),
                                buildMesh("grass_3", "Grass3.glb", 66, 66, 0, false)
                        ))
                )
                .build();
        addGrass(zone, 30);
        return ResponseEntity.ok(zone);
    }

    private void addGrass(Zone zone, int amount) {
        for (int i = 0; i < amount; i++) {
            zone.getEnvironmentElements().add(
                    buildMesh("grass_" + i,
                            "Grass" + (i % 3 + 1) + ".glb", //3 types of grass
                            Math.random() * zone.getSizeX(),
                            Math.random() * zone.getSizeZ(),
                            (int)(Math.random() * 360),
                            false)
            );
        }
    }

    private EnvironmentElement buildMesh(String name, String modelName, double x, double z, int orientationAngle, boolean collisions) {
        return EnvironmentElement.builder()
                .type("mesh")
                .name(name)
                .modelName(modelName)
                .x(x)
                .z(z)
                .orientationAngle(orientationAngle)
                .collisions(collisions)
                .build();
    }
}
