package com.supecars.cardealermanagement.service;

import com.supecars.cardealermanagement.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    List<Inventory> getAllInventories();

    Optional<Inventory> getInventoryById(int id);

    void addNewInventory(Inventory car);

    void deleteInventory(int id);

    void updateInventory(int id, Inventory updatedInventory);

    boolean isVinAlreadyInInventory(String vin);

}
