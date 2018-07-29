package com.vahundos.tracking.service;

import com.vahundos.tracking.entity.Location;
import com.vahundos.tracking.entity.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// this service simulate tracks moving
@Service
public class MockTransportMovingService implements TransportMovingService {

    static final double MOVING_STEP = 0.001;

    private final TransportService transportService;

    @Autowired
    public MockTransportMovingService(TransportService transportService) {
        this.transportService = transportService;
    }

    @Override
    @Transactional
    public void move() {
        List<Transport> transportList = transportService.getAll();
        for (Transport transport : transportList) {
            Location curLocation = transport.getLocation();
            transport.setLocation(new Location(getNewDoubleBasedOn(curLocation.getLatitude()),
                    getNewDoubleBasedOn(curLocation.getLongitude())));
        }

        transportService.updateAll(transportList);
    }

    private double getNewDoubleBasedOn(double oldValue) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            return oldValue + MOVING_STEP;
        } else {
            return oldValue - MOVING_STEP;
        }
    }
}
