package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.CustomerDao;
import com.supecars.cardealermanagement.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer1, customer2, updatedCustomer;

    @BeforeEach
    void setUp(){

        customer1 = new Customer("Adam", "Nowak", "Ny veien", "Tonsberg", "3230", "Norway", "45632474", "a.nowak@email.com", 1);
        customer2 = new Customer("Julie", "Nygaard", "Vest byen", "Holmestrand", "3080", "Norway", "79632874", "julie@email.com", 2);
        updatedCustomer = new Customer("Julie", "Nygaard", "Nord veien", "Sandefjord", "3330", "Norway", "79632874", "julie@email.com", 2);
    }

    @Test
    void it_should_give_all_customers() {
        //given
        List<Customer> expectedCustomers = Arrays.asList(customer1, customer2);
        when(customerDao.findAll()).thenReturn(expectedCustomers);

        //when
        List<Customer> actualCustomers = customerService.getAllCustomers();

        //then
        assertNotNull(actualCustomers);
        assertEquals(expectedCustomers.size(), actualCustomers.size());
        verify(customerDao, times(1)).findAll();
    }

    @Test
    void it_should_give_customer_by_id() {
        //given
        int customerId = customer1.getCustomerId();
        when(customerDao.findById(customerId)).thenReturn(Optional.of(customer1));

        //when
        Optional<Customer> foundCustomer = customerService.getCustomerById(customerId);

        //then
        assertTrue(foundCustomer.isPresent());
        assertEquals(customerId, foundCustomer.get().getCustomerId());
        verify(customerDao, times(1)).findById(customerId);
    }

    @Test
    void it_should_add_new_customer() {
        //given
        when(customerDao.save(any(Customer.class))).thenReturn(customer1);

        //when
        customerService.addNewCustomer(customer1);

        //then
        verify(customerDao, times(1)).save(customer1);
    }

    @Test
    void it_should_delete_customer_by_id() {
        //given
        int customerId = customer1.getCustomerId();
        doNothing().when(customerDao).deleteById(customerId);

        //when
        customerService.deleteCustomer(customerId);

        //then
        verify(customerDao, times(1)).deleteById(customerId);
    }

    @Test
    void it_should_update_customer_by_id() {
        //given
        int customerId = customer2.getCustomerId();
        when(customerDao.findById(customerId)).thenReturn(Optional.of(customer2));

        //when
        customerService.updateCustomer(customerId, updatedCustomer);

        //then
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerDao).save(customerCaptor.capture());
        Customer savedCustomer = customerCaptor.getValue();

        assertNotNull(savedCustomer);
        assertEquals(updatedCustomer.getFirstName(), savedCustomer.getFirstName());
        assertEquals(updatedCustomer.getLastName(), savedCustomer.getLastName());
        assertEquals(updatedCustomer.getStreet(), savedCustomer.getStreet());
        assertEquals(updatedCustomer.getCity(), savedCustomer.getCity());
        assertEquals(updatedCustomer.getPostalCode(), savedCustomer.getPostalCode());
        assertEquals(updatedCustomer.getCountry(), savedCustomer.getCountry());
        assertEquals(updatedCustomer.getPhoneNumber(), savedCustomer.getPhoneNumber());
        assertEquals(updatedCustomer.getEmail(), savedCustomer.getEmail());
        verify(customerDao, times(1)).findById(customerId);
        verify(customerDao, times(1)).save(any(Customer.class));
    }
}