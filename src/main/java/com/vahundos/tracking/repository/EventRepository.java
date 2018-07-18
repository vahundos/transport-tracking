package com.vahundos.tracking.repository;

import com.vahundos.tracking.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> getAllByTransportId(int transportId);
}
