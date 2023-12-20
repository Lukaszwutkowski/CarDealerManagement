package com.supecars.cardealermanagement.service;

import com.supecars.cardealermanagement.model.Sale;
import com.supecars.cardealermanagement.model.ServiceEntry;

import java.util.List;
import java.util.Optional;

public interface ServiceEntryService {

    List<ServiceEntry> getAllRegisteredServices();

    Optional<ServiceEntry> getServiceEntryById(int id);

    void addNewServiceEntry(ServiceEntry serviceEntry);

    void deleteServiceEntry(int id);

    void updateServiceEntry(int id, ServiceEntry updatedServiceEntry);
}
