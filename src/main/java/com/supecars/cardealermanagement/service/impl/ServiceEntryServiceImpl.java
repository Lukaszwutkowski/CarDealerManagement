package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.ServiceEntryDao;
import com.supecars.cardealermanagement.exception.ServiceEntryNotFoundException;
import com.supecars.cardealermanagement.model.ServiceEntry;
import com.supecars.cardealermanagement.service.ServiceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEntryServiceImpl implements ServiceEntryService {

    private final ServiceEntryDao serviceEntryDao;
    @Autowired
    public ServiceEntryServiceImpl(ServiceEntryDao serviceEntryDao) {
        this.serviceEntryDao = serviceEntryDao;
    }

    @Override
    public List<ServiceEntry> getAllRegisteredServices() {
        return (List<ServiceEntry>) serviceEntryDao.findAll();
    }

    @Override
    public Optional<ServiceEntry> getServiceEntryById(int id) {
        return serviceEntryDao.findById(id);
    }

    @Override
    public void addNewServiceEntry(ServiceEntry serviceEntry) {
        serviceEntryDao.save(serviceEntry);
    }

    @Override
    public void deleteServiceEntry(int id) {
        serviceEntryDao.deleteById(id);
    }

    @Override
    public void updateServiceEntry(int id, ServiceEntry updatedServiceEntry) {
        ServiceEntry existingServiceEntry = serviceEntryDao.findById(id)
                .orElseThrow(() -> new ServiceEntryNotFoundException("Service not found"));

        updateServiceEntryDetails(existingServiceEntry, updatedServiceEntry);

        serviceEntryDao.save(existingServiceEntry);
    }

    private void updateServiceEntryDetails(ServiceEntry existingServiceEntry, ServiceEntry updatedServiceEntry) {
        Optional.ofNullable(updatedServiceEntry.getCar()).ifPresent(existingServiceEntry::setCar);
        Optional.ofNullable(updatedServiceEntry.getServiceDate()).ifPresent(existingServiceEntry::setServiceDate);
        Optional.ofNullable(updatedServiceEntry.getDescription()).ifPresent(existingServiceEntry::setDescription);
        Optional.ofNullable(updatedServiceEntry.getCost()).ifPresent(existingServiceEntry::setCost);
    }


}
