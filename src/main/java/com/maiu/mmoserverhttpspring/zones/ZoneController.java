package com.maiu.mmoserverhttpspring.zones;

import com.maiu.mmoserverhttpspring.commons.dtos.zone.EnvironmentElement;
import com.maiu.mmoserverhttpspring.commons.dtos.zone.Zone;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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

    static Zone zone = Zone.builder()
            .id("zoneId")
            .name("BorkiSands")
            .sizeX(100)
            .sizeZ(100)
            .environmentElements(new ArrayList<>(List.of(
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
                                    .collisionsType("rectangle")
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
                                    .collisionsType("rectangle")
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
                                    .collisionsType("rectangle")
                                    .build(),
                            buildMesh("tree_1", "Tree1.glb", 17, 17, 0, true, "circle", 1),
                            buildMesh("tree_2", "Tree1.glb", 55, 17, 222, true, "circle", 1),
                            buildMesh("tree_3", "Tree4.glb", 22, 17, 22, true, "circle", 1),
                            buildMesh("tree_4", "Tree4.glb", 12, 77, 66, true, "circle", 1),
                            buildMesh("tree_4", "Tree4.glb", 1, 33, 66, true, "circle", 1),
                            buildMesh("tree_4", "Tree4.glb", 15, 44, 66, true, "circle", 1),
                            buildMesh("tree_4", "Tree4.glb", 37, 24, 66, true, "circle", 1),
                            buildMesh("tree_5", "Tree1.glb", 2, 18, 15, true, "circle", 1),
                            buildMeshRectangle("rock_1", "Rock1.glb", 65, 18, 15, true, 4, 4),
                            buildMeshRectangle("rock_2", "Rock1.glb", 50, 18, 45, true, 4, 4),
                            buildMeshRectangle("rock_3", "Rock2.glb", 44, 82, 233, true, 3,3),
                            buildMeshRectangle("rock_4", "Rock2.glb", 88, 18, 200, true, 3,3),
                            buildMeshRectangle("rock_5", "Rock2.glb", 66, 66, 0, true, 3,3),
                            buildMesh("grass_1", "Grass1.glb", 44, 82, 233, false, "circle", 1),
                            buildMesh("grass_2", "Grass2.glb", 88, 18, 200, false, "circle", 1),
                            buildMesh("grass_3", "Grass3.glb", 66, 66, 0, false, "circle", 1)
                    ))
            )
            .build();

    @GetMapping("/{zoneId}")
    public ResponseEntity<Zone> getZone(@PathVariable("zoneId") String zoneId) {
        log.info("Getting zone by id: {}", zoneId);
        Zone zone = ZoneController.zone;
        zone.setId(zoneId);// ;D
        addGrass(zone, 10);
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

    private static EnvironmentElement buildMesh(String name, String modelName, double x, double z, int orientationAngle, boolean collisions, String collisionType, double radius) {
        return EnvironmentElement.builder()
                .type("mesh")
                .name(name)
                .modelName(modelName)
                .x(x)
                .z(z)
                .orientationAngle(orientationAngle)
                .collisions(collisions)
                .collisionsType(collisionType)
                .radius(radius)
                .build();
    }

    private static EnvironmentElement buildMeshRectangle(String name, String modelName, double x, double z, int orientationAngle, boolean collisions, double lengthX, double lengthZ) {
        return EnvironmentElement.builder()
                .type("mesh")
                .name(name)
                .modelName(modelName)
                .x(x)
                .z(z)
                .orientationAngle(orientationAngle)
                .collisions(collisions)
                .collisionsType("rectangle")
                .lengthX(lengthX)
                .lengthZ(lengthZ)
                .build();
    }

    private static EnvironmentElement buildMesh(String name, String modelName, double x, double z, int orientationAngle, boolean collisions) {
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

    static {
        //calculating boundingBoxes etc...
        StringBuilder builder = new StringBuilder();
        for (EnvironmentElement element : zone.getEnvironmentElements()) {
            if (element.isCollisions()) {
                if (element.collisionsType.equals("circle")) {
                    Box box = calculateBoundingBoxCircle(element.x, element.z, element.radius);
                    builder.append(String.format("Circle   : '%s', x: %s, z: %s, radius: %s", element.name, element.x, element.z, element.radius));
                    builder.append("\n");
                    builder.append(String.format("CircleBox: '%s', x1: %s, x2: %s, z1: %s, z2: %s", element.name, box.x1, box.x2, box.z1, box.z2));
                    builder.append("\n");
                } else if (element.collisionsType.equals("rectangle")) {
                    //calculate box:
                    Box box = calculateBoundingBoxRectangle(element.x, element.z, element.lengthX, element.lengthZ, element.orientationAngle);
                    builder.append(String.format("Rectangle Box: '%s', x1: %s, x2: %s, z1: %s, z2: %s", element.name, box.x1, box.x2, box.z1, box.z2));
                    builder.append("\n");
                    //calculate vertices
                    List<Point> vertices = calculateVerticesRectangle(element.x, element.z, element.lengthX, element.lengthZ, element.orientationAngle);
                    builder.append(String.format("Rectangle    : '%s', x1: %s, z1: %s, x2: %s, z2: %s, x3: %s, z3: %s, x4: %s, z4: %s",
                            element.name,
                            vertices.get(0).x, vertices.get(0).z,
                            vertices.get(1).x, vertices.get(1).z,
                            vertices.get(2).x, vertices.get(2).z,
                            vertices.get(3).x, vertices.get(3).z
                            ));
                    builder.append("\n");
                }
            }
        }

        System.out.println("////////////////////////////////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////////////////////////////////");
        System.out.println(builder);
        System.out.println("////////////////////////////////////////////////////////////////////////");
        System.out.println("////////////////////////////////////////////////////////////////////////");
    }


//      BOUNDING BOX POLYGONS!
//    let points = [
//    { x: 125, y: 50 },
//    { x: 250, y: 65 },
//    { x: 300, y: 125 },
//    { x: 175, y: 175 },
//    { x: 100, y: 125 },
//            ];
//    let minX = Math.min(...points.map(point => point.x));
//    let minY = Math.min(...points.map(point => point.y));
//    let maxX = Math.max(...points.map(point => point.x));
//    let maxY = Math.max(...points.map(point => point.y));
//    let pivot = {
//            x: maxX - ((maxX - minX) / 2),
//    y: maxY - ((maxY - minY) / 2)
//};
//let degrees = 90;
//        let radians = degrees * (Math.PI / 180);
//        let cos = Math.cos(radians);
//        let sin = Math.sin(radians);
//
//        function rotatePoint(pivot, point, cos, sin) {
//        return {
//        x: (cos * (point.x - pivot.x)) - (sin * (point.y - pivot.y)) + pivot.x,
//        y: (sin * (point.x - pivot.x)) + (cos * (point.y - pivot.y)) + pivot.y
//        };
//        }
//
//        let boundingBox = {
//        x1: Number.POSITIVE_INFINITY,
//        y1: Number.POSITIVE_INFINITY,
//        x2: Number.NEGATIVE_INFINITY,
//        y2: Number.NEGATIVE_INFINITY,
//        };
//
//        points.forEach((point) => {
//        let rotatedPoint = rotatePoint(pivot, point, cos, sin);
//
//        boundingBox.x1 = Math.min(boundingBox.x1, rotatedPoint.x);
//        boundingBox.y1 = Math.min(boundingBox.y1, rotatedPoint.y);
//        boundingBox.x2 = Math.max(boundingBox.x2, rotatedPoint.x);
//        boundingBox.y2 = Math.max(boundingBox.y2, rotatedPoint.y);
//        });

    static Box calculateBoundingBoxRectangle(double x, double z, double lengthX, double lengthZ, int rotationAngle) {
        Point point1 = new Point(x + lengthX/2, z + lengthZ/2);
        Point point2 = new Point(x + lengthX/2, z - lengthZ/2);
        Point point3 = new Point(x - lengthX/2, z - lengthZ/2);
        Point point4 = new Point(x - lengthX/2, z + lengthZ/2);
        Point pivot = new Point(x, z);

        Point rotated1 = rotatePoint(pivot, point1, rotationAngle);
        Point rotated2 = rotatePoint(pivot, point2, rotationAngle);
        Point rotated3 = rotatePoint(pivot, point3, rotationAngle);
        Point rotated4 = rotatePoint(pivot, point4, rotationAngle);


        return new Box(
                Math.min(Math.min(rotated1.x, rotated2.x), Math.min(rotated3.x, rotated4.x)),
                Math.max(Math.max(rotated1.x, rotated2.x), Math.max(rotated3.x, rotated4.x)),
                Math.min(Math.min(rotated1.z, rotated2.z), Math.min(rotated3.z, rotated4.z)),
                Math.max(Math.max(rotated1.z, rotated2.z), Math.max(rotated3.z, rotated4.z))
        );
    }

    static List<Point> calculateVerticesRectangle(double x, double z, double lengthX, double lengthZ, int rotationAngle) {
        //order matters
        Point point1 = new Point(x + lengthX/2, z + lengthZ/2);
        Point point2 = new Point(x + lengthX/2, z - lengthZ/2);
        Point point3 = new Point(x - lengthX/2, z - lengthZ/2);
        Point point4 = new Point(x - lengthX/2, z + lengthZ/2);
        Point pivot = new Point(x, z);

        Point rotated1 = rotatePoint(pivot, point1, rotationAngle);
        Point rotated2 = rotatePoint(pivot, point2, rotationAngle);
        Point rotated3 = rotatePoint(pivot, point3, rotationAngle);
        Point rotated4 = rotatePoint(pivot, point4, rotationAngle);


        return List.of(rotated1, rotated2, rotated3, rotated4);
    }

    static Point rotatePoint(Point pivot, Point toRotate, int Angle) {
        double radians = -Angle * (Math.PI / 180);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double newX = (cos * (toRotate.x - pivot.x)) - (sin * (toRotate.z - pivot.z)) + pivot.x;
        double nexZ = (sin * (toRotate.x - pivot.x)) + (cos * (toRotate.z - pivot.z)) + pivot.z;
        return new Point(newX, nexZ);
    }

    @AllArgsConstructor
    static class Point {
        double x;
        double z;
    }



    //BOUNDING BOX CIRCLE
//    let centerX = 350;
//    let centerY = 100;
//    let radiusX = 100;
//    let radiusY = 50;
//    let degrees = 200;
//
//    let radians = degrees * (Math.PI / 180);
//    let radians90 = radians + Math.PI / 2;
//    let ux = radiusX * Math.cos(radians);
//    let uy = radiusX * Math.sin(radians);
//    let vx = radiusY * Math.cos(radians90);
//    let vy = radiusY * Math.sin(radians90);
//
//    let width = Math.sqrt(ux * ux + vx * vx) * 2;
//    let height = Math.sqrt(uy * uy + vy * vy) * 2;
//    let x = centerX - (width / 2);
//    let y = centerY - (height / 2);

    static Box calculateBoundingBoxCircle(double x, double z, double radius) {
        return new Box(x - radius, x + radius, z - radius, z + radius);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Box {
        double x1;
        double x2;
        double z1;
        double z2;
    }
}
