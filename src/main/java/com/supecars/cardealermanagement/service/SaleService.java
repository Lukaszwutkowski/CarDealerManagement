package com.supecars.cardealermanagement.service;

import com.supecars.cardealermanagement.model.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleService {

    List<Sale> getAllSales();

    Optional<Sale> getSaleById(int id);

    void addNewSale(Sale sale);

    void deleteSale(int id);

    void updateSale(int id, Sale updatedSale);
}
