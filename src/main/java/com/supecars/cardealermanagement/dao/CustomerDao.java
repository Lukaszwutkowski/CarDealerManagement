package com.supecars.cardealermanagement.dao;

import com.supecars.cardealermanagement.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {
}
