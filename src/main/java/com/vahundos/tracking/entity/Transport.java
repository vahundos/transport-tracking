package com.vahundos.tracking.entity;

import lombok.*;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Transport extends AbstractBaseEntity {

    private Location location;
}
