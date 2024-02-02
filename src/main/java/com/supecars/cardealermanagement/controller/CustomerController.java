package com.supecars.cardealermanagement.controller;

import com.supecars.cardealermanagement.model.Customer;
import com.supecars.cardealermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String getAllCustomers(Model model){
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }
    @GetMapping("/addCustomer")
    public String addCustomerForm(Model model){
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }

    @GetMapping("/editCustomer/{id}")
    public String editCustomerForm(@PathVariable int id, Model model){
        return customerService.getCustomerById(id)
                .map(customer -> {
                    model.addAttribute("customer", customer);
                    return "editCustomer";
                })
                .orElse("redirect:/customers");
    }

    @PostMapping("/customers")
    public String addCustomer(@ModelAttribute Customer customer){
        customerService.addNewCustomer(customer);
        return "redirect:/customers";
    }

    @PostMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable int id){
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    @PostMapping("/update_customer/{id}")
    public String updateCustomer(@PathVariable int id, @ModelAttribute Customer customer){
        customerService.updateCustomer(id, customer);
        return "redirect:/customers";
    }
}
