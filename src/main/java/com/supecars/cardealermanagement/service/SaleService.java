package com.supecars.cardealermanagement.service;

import com.supecars.cardealermanagement.dto.SaleDto;
import com.supecars.cardealermanagement.model.Inventory;
import com.supecars.cardealermanagement.model.Sale;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public interface SaleService {

    List<Sale> getAllSales();

    Optional<Sale> getSaleById(int id);

    void addNewSale(Sale sale);

    void deleteSale(int id);

    void updateSale(SaleDto saleDto);

    public boolean canSellCar(String vin);

    public Sale createSale(SaleDto saleDto);

    public boolean isVinAlreadySold(String vin);

}
