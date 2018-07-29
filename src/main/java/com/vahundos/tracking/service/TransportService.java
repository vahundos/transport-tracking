package com.vahundos.tracking.service;

import com.vahundos.tracking.entity.Transport;

import java.util.List;

public interface TransportService {

    List<Transport> getAll();

    Transport create(Transport transport);

    List<Transport> updateAll(Iterable<Transport> iterable);
}
