package com.vahundos.tracking.service;

import com.vahundos.tracking.entity.Event;
import com.vahundos.tracking.repository.EventRepository;
import com.vahundos.tracking.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    @Autowired
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event create(Event event) {
        ValidationUtils.validateForCreation(event);
        return repository.save(event);
    }

    @Override
    public List<Event> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Event> getAllByTransportId(int transportId) {
        return repository.getAllByTransportId(transportId);
    }
}
