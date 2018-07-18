package com.vahundos.tracking.service;

import com.vahundos.tracking.AbstractBaseTest;
import com.vahundos.tracking.entity.Transport;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.vahundos.tracking.service.MockTransportMovingService.MOVING_STEP;

public class MockTransportMovingServiceTest extends AbstractBaseTest {

    @Autowired
    private TransportMovingService movingService;

    @Autowired
    private TransportService transportService;

    @Test
    public void moveTest() {
        List<Transport> transportBefore = transportService.getAll();
        movingService.move();
        List<Transport> transportAfter = transportService.getAll();

        Assert.assertEquals("Size before/after should be same", transportBefore.size(), transportAfter.size());

        for (int i = 0; i < transportBefore.size(); i++) {
            Transport before = transportBefore.get(i);
            Transport after = transportAfter.get(i);
            Assert.assertEquals("Order before/after should be same", before.getId(), after.getId());

            double deltaLng = Math.abs(before.getLocation().getLongitude() - after.getLocation().getLongitude());
            double deltaLat = Math.abs(before.getLocation().getLatitude() - after.getLocation().getLatitude());

            Assert.assertEquals("Lng delta should be about MOVING_STEP", MOVING_STEP, deltaLng, 0.000001);
            Assert.assertEquals("Lat delta should be about MOVING_STEP", MOVING_STEP, deltaLat, 0.000001);
        }
    }

}