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
    public double x;
    public double z;
    public double y;
    public double lengthX;
    public double lengthZ;
    public double lengthY;
    public double orientationAngle;
    public boolean collisions;
}
