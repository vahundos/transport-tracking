package com.vahundos.tracking.web;

import com.vahundos.tracking.entity.Event;
import com.vahundos.tracking.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ajax/event")
public class EventAjaxController {

    private static final Logger log = LoggerFactory.getLogger(EventAjaxController.class);

    private final EventService service;

    @Autowired
    public EventAjaxController(EventService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Event> getTransportId(@RequestParam(required = false) Integer transportId) {
        if (transportId != null) {
            log.debug("get events by transportId = {}", transportId);
            return service.getAllByTransportId(transportId);
        } else {
            log.debug("get all events");
            return service.getAll();
        }
    }
}
