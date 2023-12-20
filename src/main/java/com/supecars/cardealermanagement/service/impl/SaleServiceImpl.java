package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.SaleDao;
import com.supecars.cardealermanagement.exception.SaleNotFoundException;
import com.supecars.cardealermanagement.model.Sale;
import com.supecars.cardealermanagement.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleDao saleDao;
    @Autowired
    public SaleServiceImpl(SaleDao saleDao) {
        this.saleDao = saleDao;
    }

    @Override
    public List<Sale> getAllSales() {
        return (List<Sale>) saleDao.findAll();
    }

    @Override
    public Optional<Sale> getSaleById(int id) {
        return saleDao.findById(id);
    }

    @Override
    public void addNewSale(Sale sale) {
        saleDao.save(sale);
    }

    @Override
    public void deleteSale(int id) {
        saleDao.deleteById(id);
    }

    @Override
    public void updateSale(int id, Sale updatedSale) {
        Sale existingSale = saleDao.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale with id: " + id + " not found"));

        updateSaleDetails(existingSale, updatedSale);

        saleDao.save(existingSale);
    }

    private void updateSaleDetails(Sale existingSale, Sale updatedSale) {
        Optional.ofNullable(updatedSale.getCustomer()).ifPresent(existingSale::setCustomer);
        Optional.ofNullable(updatedSale.getCar()).ifPresent(existingSale::setCar);
        Optional.ofNullable(updatedSale.getEmployee()).ifPresent(existingSale::setEmployee);
        Optional.ofNullable(updatedSale.getSaleDate()).ifPresent(existingSale::setSaleDate);
        Optional.ofNullable(updatedSale.getSalePrice()).ifPresent(existingSale::setSalePrice);
    }
}
