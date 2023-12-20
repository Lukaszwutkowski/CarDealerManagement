package com.supecars.cardealermanagement.controller;

import com.supecars.cardealermanagement.model.ServiceEntry;
import com.supecars.cardealermanagement.service.ServiceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceEntryController {

    private final ServiceEntryService serviceEntryService;

    @Autowired
    public ServiceEntryController(ServiceEntryService serviceEntryService) {
        this.serviceEntryService = serviceEntryService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceEntry>> getAllServices() {
        List<ServiceEntry> services = serviceEntryService.getAllRegisteredServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntry> getServiceEntryById(@PathVariable int id) {
        return serviceEntryService.getServiceEntryById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ServiceEntry> addServiceEntry(@RequestBody ServiceEntry serviceEntry) {
        serviceEntryService.addNewServiceEntry(serviceEntry);
        return new ResponseEntity<>(serviceEntry, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceEntry> deleteServiceEntry(@PathVariable int id) {
        serviceEntryService.deleteServiceEntry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntry> updateServiceEntry(@PathVariable int id, @RequestBody ServiceEntry serviceEntry) {
        serviceEntryService.updateServiceEntry(id, serviceEntry);
        return ResponseEntity.ok(serviceEntry);
    }

}
