package com.vahundos.tracking.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "events")
public class Event extends AbstractBaseEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private EventType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Zone zone;

    @ManyToOne(fetch = FetchType.LAZY)
    private Transport transport;
}
