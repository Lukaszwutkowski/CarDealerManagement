package com.supecars.cardealermanagement.dao;

import com.supecars.cardealermanagement.model.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleDao extends CrudRepository<Sale, Integer> {

    Optional<Sale> findSaleByCarVin(@Param("vin") String vin);
}
