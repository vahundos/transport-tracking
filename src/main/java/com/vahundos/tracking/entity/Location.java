package com.vahundos.tracking.entity;

import lombok.*;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class Location {

    private double latitude;

    private double longitude;
}
