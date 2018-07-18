package com.vahundos.tracking.service;

import com.vahundos.tracking.entity.Event;

import java.util.List;

public interface EventService {

    Event create(Event event);

    List<Event> getAll();

    List<Event> getAllByTransportId(int transportId);
}
