package com.vahundos.tracking.web;

import com.vahundos.tracking.entity.Transport;
import com.vahundos.tracking.service.TransportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ajax/tracking")
public class TransportTrackingAjaxController {

    private static final Logger log = LoggerFactory.getLogger(TransportTrackingAjaxController.class);

    private final TransportService service;

    @Autowired
    public TransportTrackingAjaxController(TransportService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Transport createTransportTracking(@RequestBody Transport transport) {
        log.debug("save for tracking = {}", transport);
        return service.create(transport);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Transport> getAll() {
        log.debug("get all transport");
        return service.getAll();
    }
}
