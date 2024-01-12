package com.maiu.mmoserverhttpspring.zones;

import com.maiu.mmoserverhttpspring.commons.dtos.zone.Zone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.maiu.mmoserverhttpspring.config.Config.HTTP_ZONES_PREFIX;

@Slf4j
@RestController
@RequestMapping(HTTP_ZONES_PREFIX)
public class ZoneController {

    @GetMapping("/{zoneId}")
    public ResponseEntity<Zone> getZone(@PathVariable("zoneId") String zoneId) {
        log.info("Getting zone by id: {}", zoneId);
        return ResponseEntity.ok(Zone.builder().id(zoneId).name("BorkiSands").sizeX(100).sizeZ(100).build());
    }
}
