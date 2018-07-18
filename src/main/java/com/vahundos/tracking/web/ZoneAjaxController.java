package com.vahundos.tracking.web;

import com.vahundos.tracking.entity.Zone;
import com.vahundos.tracking.service.ZoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ajax/zone")
public class ZoneAjaxController {

    private static final Logger log = LoggerFactory.getLogger(ZoneAjaxController.class);

    private final ZoneService service;

    @Autowired
    public ZoneAjaxController(ZoneService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Zone create(@RequestBody Zone zone) {
        log.debug("save zone = {}", zone);
        return service.create(zone);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Zone> getAll() {
        log.debug("get all zones");
        return service.getAll();
    }
}
