package com.supecars.cardealermanagement.dao;

import com.supecars.cardealermanagement.model.ServiceEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceEntryDao extends CrudRepository<ServiceEntry, Integer> {
}
