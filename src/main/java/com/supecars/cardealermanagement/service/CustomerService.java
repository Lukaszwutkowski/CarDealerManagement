package com.supecars.cardealermanagement.service;

import com.supecars.cardealermanagement.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(int id);
    void addNewCustomer(Customer customer);
    void deleteCustomer(int id);
    void updateCustomer(int id, Customer updatedCustomer);
}
