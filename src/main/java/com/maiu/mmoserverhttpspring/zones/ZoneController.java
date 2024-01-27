package com.maiu.mmoserverhttpspring.zones;

import com.maiu.mmoserverhttpspring.commons.dtos.zone.EnvironmentElement;
import com.maiu.mmoserverhttpspring.commons.dtos.zone.Zone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.maiu.mmoserverhttpspring.config.Config.HTTP_ZONES_PREFIX;

@Slf4j
@RestController
@RequestMapping(HTTP_ZONES_PREFIX)
public class ZoneController {

    @GetMapping("/{zoneId}")
    public ResponseEntity<Zone> getZone(@PathVariable("zoneId") String zoneId) {
        log.info("Getting zone by id: {}", zoneId);
        return ResponseEntity.ok(
                Zone.builder()
                        .id(zoneId)
                        .name("BorkiSands")
                        .sizeX(100)
                        .sizeZ(100)
                        .environmentElements(
                                List.of(
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
                                                .build()
                                )
                        )
                        .build()
        );
    }
}
