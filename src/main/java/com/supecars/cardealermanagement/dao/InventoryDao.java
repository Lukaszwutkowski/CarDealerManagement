package com.supecars.cardealermanagement.dao;

import com.supecars.cardealermanagement.model.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDao extends CrudRepository<Inventory, Integer> {
}
