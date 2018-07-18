package com.vahundos.tracking;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.vahundos.tracking.service.TransportMovingService;
import com.vahundos.tracking.service.ZoneProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class TrackingApplication {

    private static final Logger log = LoggerFactory.getLogger(TrackingApplication.class);

    private final TransportMovingService transportMovingService;

    private final ZoneProcessorService zoneProcessorService;

    @Autowired
    public TrackingApplication(TransportMovingService transportMovingService, ZoneProcessorService zoneProcessorService) {
        this.transportMovingService = transportMovingService;
        this.zoneProcessorService = zoneProcessorService;
    }

    @Scheduled(fixedRate = 5000, initialDelay = 5000)
    public void moveTransport() {
        transportMovingService.move();
        log.debug("transport moved");
        zoneProcessorService.checkTracksPositions();
    }

    @Bean
    public Module datatypeHibernateModule() {
        Hibernate5Module module = new Hibernate5Module();
        module.configure(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);
        return module;
    }

    public static void main(String[] args) {
        SpringApplication.run(TrackingApplication.class, args);
    }
}
