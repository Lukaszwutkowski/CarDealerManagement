package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.InventoryDao;
import com.supecars.cardealermanagement.exception.InventoryNotFoundException;
import com.supecars.cardealermanagement.model.Inventory;
import com.supecars.cardealermanagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryDao inventoryDao;

    @Autowired
    public InventoryServiceImpl(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    @Override
    public List<Inventory> getAllInventories() {
        return (List<Inventory>) inventoryDao.findAll();
    }

    @Override
    public Optional<Inventory> getInventoryById(int id) {
        return inventoryDao.findById(id);
    }

    @Override
    public void addNewInventory(Inventory car) {
        inventoryDao.save(car);
    }

    @Override
    public void deleteInventory(int id) {
        inventoryDao.deleteById(id);
    }

    @Override
    public void updateInventory(int id, Inventory updatedInventory) {
        Inventory existingInventory = inventoryDao.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));

        updateInventoryDetails(existingInventory, updatedInventory);

        inventoryDao.save(existingInventory);
    }

    private void updateInventoryDetails(Inventory existingInventory, Inventory updatedInventory) {
        Optional.ofNullable(updatedInventory.getCar()).ifPresent(existingInventory::setCar);
        Optional.ofNullable(updatedInventory.getStatus()).ifPresent(existingInventory::setStatus);
        Optional.ofNullable(updatedInventory.getAcquisitionDate()).ifPresent(existingInventory::setAcquisitionDate);
        Optional.ofNullable(updatedInventory.getAcquisitionPrice()).ifPresent(existingInventory::setAcquisitionPrice);
    }
}
