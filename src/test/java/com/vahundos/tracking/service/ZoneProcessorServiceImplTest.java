package com.vahundos.tracking.service;

import com.vahundos.tracking.AbstractBaseTest;
import com.vahundos.tracking.entity.Event;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ZoneProcessorServiceImplTest extends AbstractBaseTest {

    @Autowired
    private ZoneProcessorService zoneProcessorService;

    @Autowired
    private EventService eventService;

    @Test
    public void zoneProcessingTest() {
        List<Event> allEventsBefore = eventService.getAll();
        Assert.assertEquals(0, allEventsBefore.size());

        zoneProcessorService.checkTracksPositions();

        List<Event> allEventsAfter = eventService.getAll();
        Assert.assertEquals(1, allEventsAfter.size());

        Event event = allEventsAfter.get(0);
        // data from populate_db.sql
        Assert.assertEquals("Checked zone has id = 1", 1, (int) event.getZone().getId());
        Assert.assertEquals("Transport inside zone has id = 3", 3, (int) event.getTransport().getId());
    }
}