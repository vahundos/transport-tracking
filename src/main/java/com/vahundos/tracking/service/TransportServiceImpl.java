package com.vahundos.tracking.service;

import com.vahundos.tracking.entity.Transport;
import com.vahundos.tracking.repository.TransportRepository;
import com.vahundos.tracking.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TransportServiceImpl implements TransportService {

    private final TransportRepository repository;

    @Autowired
    public TransportServiceImpl(TransportRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Transport> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Transport create(Transport transport) {
        ValidationUtils.validateForCreation(transport);
        return repository.save(transport);
    }

    @Override
    public List<Transport> updateAll(Iterable<Transport> iterable) {
        ValidationUtils.validateForUpdate(iterable);
        return repository.saveAll(iterable);
    }
}
