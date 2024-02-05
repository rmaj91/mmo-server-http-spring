package com.maiu.mmoserverhttpspring.commons.dtos.zone;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnvironmentElement {

    //bounding box info??
    public String type;
    public String name;
    public String modelName;
    public double x;
    public double z;
    public double y;
    public double lengthX;
    public double lengthZ;
    public double lengthY;
    public double radius;
    public int orientationAngle;
    public boolean collisions;
    public String collisionsType;
}
