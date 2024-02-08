package com.supecars.cardealermanagement.controller;

import com.supecars.cardealermanagement.dto.SaleDto;
import com.supecars.cardealermanagement.model.Sale;
import com.supecars.cardealermanagement.service.CarService;
import com.supecars.cardealermanagement.service.CustomerService;
import com.supecars.cardealermanagement.service.EmployeeService;
import com.supecars.cardealermanagement.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SaleController {

    private final SaleService saleService;
    private final CarService carService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    @Autowired
    public SaleController(SaleService saleService, CarService carService, CustomerService customerService, EmployeeService employeeService) {
        this.saleService = saleService;
        this.carService = carService;
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @GetMapping("/sales")
    public String getAllSales(Model model) {
        List<Sale> sales = saleService.getAllSales();
        model.addAttribute("sales", sales);
        return "sales";
    }

    @GetMapping("/addSale")
    public String addSaleForm(Model model) {
        model.addAttribute("sale", new Sale());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "addSale";
    }

    @PostMapping("/sales")
    public String addNewSale(@Valid @ModelAttribute("saleDto") SaleDto saleDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("cars", carService.getAllCars());
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "addSale";
        }
        if (saleService.isVinAlreadySold(saleDto.getVin())){
            result.rejectValue("vin", "error.sale", "This VIN is already sold");
            model.addAttribute("customers", customerService.getAllCustomers());
            model.addAttribute("cars", carService.getAllCars());
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "addSale";
        }
        saleService.createSale(saleDto);
        return "redirect:/sales";
    }
    
    @GetMapping("/editSale/{id}")
    public String editSaleForm(@PathVariable int id, Model model){
        Sale sale = saleService. getSaleById(id)
                .orElseThrow(() -> new  IllegalArgumentException("Invalid sale Id:" + id));
        model.addAttribute("sale", sale);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "editSale";
    }

    @PostMapping("/sales/delete/{id}")
    public String deleteSale(@PathVariable int id) {
        saleService.deleteSale(id);
        return "redirect:/sales";
    }

    @PostMapping("/update_sale/{id}")
    public String updateSale(@PathVariable int id, @ModelAttribute SaleDto saleDto) {
        saleDto.setSaleId(id);
        saleService.updateSale(saleDto);
        return "redirect:/sales";
    }

}
