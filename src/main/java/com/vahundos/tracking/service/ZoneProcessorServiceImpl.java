package com.vahundos.tracking.service;

import com.vahundos.tracking.entity.*;
import com.vahundos.tracking.utils.Haversine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ZoneProcessorServiceImpl implements ZoneProcessorService {

    private static final Logger log = LoggerFactory.getLogger(ZoneProcessorServiceImpl.class);

    private final ZoneService zoneService;

    private final TransportService transportService;

    private final EventService eventService;

    // transport id -> last event type
    private final TransportZoneEventTypeHolder holder = new TransportZoneEventTypeHolder();

    @Autowired
    public ZoneProcessorServiceImpl(ZoneService zoneService, TransportService transportService, EventService eventService) {
        this.zoneService = zoneService;
        this.transportService = transportService;
        this.eventService = eventService;
    }

    @Override
    @Transactional
    public void checkTracksPositions() {
        List<Transport> transportList = transportService.getAll();
        List<Zone> zones = zoneService.getAll();
        for (Transport transport : transportList) {
            for (Zone zone : zones) {
                EventType eventType;
                if (isTrackInsideZone(transport.getLocation(), zone)) {
                    eventType = EventType.ENTER;
                } else {
                    eventType = EventType.EXIT;
                }

                if (!holder.isLastEventSame(transport.getId(), zone.getId(), eventType)) {
                    Event event = new Event();
                    event.setDate(LocalDateTime.now());
                    event.setType(eventType);
                    event.setTransport(transport);
                    event.setZone(zone);
                    Event save = eventService.create(event);
                    log.debug("created event {}", save);
                }
            }
        }
    }

    private boolean isTrackInsideZone(Location track, Zone zone) {
        Location zoneLocation = zone.getLocation();
        double distance = Haversine.distance(track.getLatitude(), track.getLongitude(),
                zoneLocation.getLatitude(), zoneLocation.getLongitude());
        // convert distance to meters
        return distance * 1000 < zone.getRadius();
    }
}
