package com.vahundos.tracking.service;

import com.vahundos.tracking.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> getAll();

    List<Event> getAllByTransportId(int transportId);
}
