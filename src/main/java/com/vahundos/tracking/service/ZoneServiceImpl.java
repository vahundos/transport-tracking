package com.vahundos.tracking.service;

import com.vahundos.tracking.entity.Zone;
import com.vahundos.tracking.repository.ZoneRepository;
import com.vahundos.tracking.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repository;

    @Autowired
    public ZoneServiceImpl(ZoneRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable("zones")
    public List<Zone> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional

    @CacheEvict(value = "zones", allEntries = true)
    public Zone create(Zone zone) {
        ValidationUtils.validateForCreation(zone);
        return repository.save(zone);
    }
}
