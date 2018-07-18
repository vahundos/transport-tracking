package com.vahundos.tracking.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "zones")
public class Zone extends AbstractBaseEntity {

    private Location location;

    @Positive
    private double radius;
}
