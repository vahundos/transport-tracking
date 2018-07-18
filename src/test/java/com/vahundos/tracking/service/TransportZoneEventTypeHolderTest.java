package com.vahundos.tracking.service;

import com.vahundos.tracking.entity.EventType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransportZoneEventTypeHolderTest {

    private TransportZoneEventTypeHolder holder;

    @Before
    public void setUp() throws Exception {
        holder = new TransportZoneEventTypeHolder();
    }

    @Test
    public void exitEventForEmptyTest() {
        boolean lastEventSame = holder.isLastEventSame(1, 1, EventType.EXIT);
        assertTrue("For empty holder EXIT event is same", lastEventSame);
    }

    @Test
    public void enterEventForEmptyTest() {
        boolean lastEventSame = holder.isLastEventSame(1, 1, EventType.ENTER);
        assertFalse("For empty holder ENTER event is new", lastEventSame);
    }

    @Test
    public void exitEventForExistSameTest() {
        holder.isLastEventSame(1, 1, EventType.ENTER);
        boolean lastEventSame = holder.isLastEventSame(1, 1, EventType.EXIT);
        assertFalse("For same zone and transport with ENTER event at start EXIT event is new", lastEventSame);
    }

    @Test
    public void exitEventForExistTransportTest() {
        holder.isLastEventSame(1, 1, EventType.ENTER);
        boolean lastEventSame = holder.isLastEventSame(1, 2, EventType.EXIT);
        assertTrue("For same transport and different zone with ENTER event at start EXIT event is same", lastEventSame);
    }

    @Test
    public void exitEventForExistZoneTest() {
        holder.isLastEventSame(1, 1, EventType.ENTER);
        boolean lastEventSame = holder.isLastEventSame(2, 1, EventType.EXIT);
        assertTrue("For different transport and same zone with ENTER event at start EXIT event is same", lastEventSame);
    }

    @Test
    public void enterEventForExistSameTest() {
        holder.isLastEventSame(1, 1, EventType.ENTER);
        boolean lastEventSame = holder.isLastEventSame(1, 1, EventType.ENTER);
        assertTrue("For same transport and zone with ENTER event at start ENTER event is same", lastEventSame);
    }

    @Test
    public void enterEventForExistTransportTest() {
        holder.isLastEventSame(1, 1, EventType.ENTER);
        boolean lastEventSame = holder.isLastEventSame(1, 2, EventType.ENTER);
        assertFalse("For same transport and different zone with ENTER event at start ENTER event is same",
                lastEventSame);
    }

    @Test
    public void enterEventForExistZoneTest() {
        holder.isLastEventSame(1, 1, EventType.ENTER);
        boolean lastEventSame = holder.isLastEventSame(2, 1, EventType.ENTER);
        assertFalse("For different transport and same zone with ENTER event at start ENTER event is not same",
                lastEventSame);
    }
}