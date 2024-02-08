package com.supecars.cardealermanagement.service.impl;


import com.supecars.cardealermanagement.dao.SaleDao;
import com.supecars.cardealermanagement.dto.SaleDto;
import com.supecars.cardealermanagement.exception.SaleNotFoundException;
import com.supecars.cardealermanagement.model.*;
import com.supecars.cardealermanagement.service.CarService;
import com.supecars.cardealermanagement.service.CustomerService;
import com.supecars.cardealermanagement.service.EmployeeService;
import com.supecars.cardealermanagement.service.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleDao saleDao;
    private final CustomerService customerService;

    private final EmployeeService employeeService;

    private final CarService carService;

    public SaleServiceImpl(SaleDao saleDao, CustomerServiceImpl customerService, EmployeeServiceImpl employeeService, CarServiceImpl carService) {
        this.saleDao = saleDao;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.carService = carService;
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
    public void updateSale(SaleDto saleDto) {
        Sale sale = saleDao.findById(saleDto.getSaleId())
                .orElseThrow(() -> new SaleNotFoundException("Sale with id: " + saleDto.getSaleId() + " not found"));
        Customer customer = customerService.getCustomerById(saleDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Employee employee = employeeService.getEmployeeById(saleDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Car car = carService.getCarByVinNumber(saleDto.getVin())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        sale.setCustomer(customer);
        sale.setEmployee(employee);
        sale.setCar(car);
        sale.setSaleDate(saleDto.getSaleDate());
        sale.setSalePrice(saleDto.getSalePrice());

        saleDao.save(sale);
    }

    private void updateSaleDetails(Sale existingSale, Sale updatedSale) {
        Optional.ofNullable(updatedSale.getCustomer()).ifPresent(existingSale::setCustomer);
        Optional.ofNullable(updatedSale.getCar()).ifPresent(existingSale::setCar);
        Optional.ofNullable(updatedSale.getEmployee()).ifPresent(existingSale::setEmployee);
        Optional.ofNullable(updatedSale.getSaleDate()).ifPresent(existingSale::setSaleDate);
        Optional.ofNullable(updatedSale.getSalePrice()).ifPresent(existingSale::setSalePrice);
    }

    @Override
    public boolean canSellCar(String vin) {
        return saleDao.findSaleByCarVin(vin).isEmpty();
    }

    @Override
    public Sale createSale(SaleDto saleDto) {
        System.out.println("Trying to find customer with ID: " + customerService.getCustomerById(saleDto.getCustomerId()));
        Customer customer = customerService.getCustomerById(saleDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Employee employee = employeeService.getEmployeeById(saleDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Car car = carService.getCarByVinNumber(saleDto.getVin())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setEmployee(employee);
        sale.setCar(car);
        sale.setSaleDate(saleDto.getSaleDate());
        sale.setSalePrice(saleDto.getSalePrice());

        return saleDao.save(sale);
    }

    @Override
    public boolean isVinAlreadySold(String vin) {
        Iterable<Sale> allSales = saleDao.findAll();
        return StreamSupport.stream(allSales.spliterator(), false)
                .anyMatch(inventory -> inventory.getCar() != null && inventory.getCar().getVin().equals(vin));
    }
}

