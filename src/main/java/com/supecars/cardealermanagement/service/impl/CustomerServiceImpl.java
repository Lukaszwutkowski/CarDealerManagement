package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.CustomerDao;
import com.supecars.cardealermanagement.exception.CustomerNotFoundExeption;
import com.supecars.cardealermanagement.model.Customer;
import com.supecars.cardealermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerDao.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {
        return customerDao.findById(id);
    }

    @Override
    public void addNewCustomer(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerDao.deleteById(id);
    }

    @Override
    public void updateCustomer(int id, Customer updatedCustomer) {
        Customer existingCustomer = customerDao.findById(id)
                .orElseThrow(() -> new CustomerNotFoundExeption("Customer not found"));

        udateCustomerDetails(existingCustomer, updatedCustomer);

        customerDao.save(existingCustomer);

    }

    private void udateCustomerDetails(Customer existingCustomer, Customer updatedCustomer) {
        Optional.ofNullable(updatedCustomer.getFirstName()).ifPresent(existingCustomer::setFirstName);
        Optional.ofNullable(updatedCustomer.getLastName()).ifPresent(existingCustomer::setLastName);
        Optional.ofNullable(updatedCustomer.getStreet()).ifPresent(existingCustomer::setStreet);
        Optional.ofNullable(updatedCustomer.getCity()).ifPresent(existingCustomer::setCity);
        Optional.ofNullable(updatedCustomer.getPostalCode()).ifPresent(existingCustomer::setPostalCode);
        Optional.ofNullable(updatedCustomer.getCountry()).ifPresent(existingCustomer::setCountry);
        Optional.ofNullable(updatedCustomer.getPhoneNumber()).ifPresent(existingCustomer::setPhoneNumber);
        Optional.ofNullable(updatedCustomer.getEmail()).ifPresent(existingCustomer::setEmail);
    }
}
