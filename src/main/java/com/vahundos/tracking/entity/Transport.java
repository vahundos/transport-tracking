package com.vahundos.tracking.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Transport extends AbstractBaseEntity {

    @NotNull
    private Location location;
}
