package com.vahundos.tracking.service;

import com.vahundos.tracking.entity.Zone;

import java.util.List;

public interface ZoneService {

    List<Zone> getAll();

    Zone create(Zone zone);
}
