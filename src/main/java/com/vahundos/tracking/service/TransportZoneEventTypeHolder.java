package com.vahundos.tracking.service;

import com.vahundos.tracking.entity.EventType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@ToString
@EqualsAndHashCode
class TransportZoneEventTypeHolder {

    // transport id -> [zone id -> status]
    private Map<Integer, Map<Integer, EventType>> map = new ConcurrentHashMap<>();

    /*
    true - if last event same
    false - if event new
    also return true for EXIT event if it first, so its means don`t create event in db
     */
    boolean isLastEventSame(int transportId, int zoneId, EventType eventType) {
        if (!map.containsKey(transportId)) {
            if (eventType == EventType.EXIT) {
                return true;
            } else {
                putZoneWithStatus(transportId, zoneId, eventType);
                return false;
            }
        } else {
            Map<Integer, EventType> zoneStatusMap = map.get(transportId);
            if (!zoneStatusMap.containsKey(zoneId)) {
                if (eventType == EventType.EXIT) {
                    return true;
                } else {
                    putZoneWithStatus(transportId, zoneId, eventType);
                    return false;
                }
            } else {
                boolean isSame = zoneStatusMap.get(zoneId) == eventType;
                putZoneWithStatus(transportId, zoneId, eventType);
                return isSame;
            }
        }
    }

    private void putZoneWithStatus(int transportId, int zoneId, EventType eventType) {
        if (map.containsKey(transportId)) {
            Map<Integer, EventType> zoneStatusMap = map.get(transportId);
            zoneStatusMap.put(zoneId, eventType);
        } else {
            Map<Integer, EventType> zoneStatusMap = new ConcurrentHashMap<>();
            zoneStatusMap.put(zoneId, eventType);
            map.put(transportId, zoneStatusMap);
        }
    }
}


