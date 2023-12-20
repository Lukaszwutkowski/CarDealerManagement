package com.supecars.cardealermanagement.dao;

import com.supecars.cardealermanagement.model.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDao extends CrudRepository<Sale, Integer> {
}
