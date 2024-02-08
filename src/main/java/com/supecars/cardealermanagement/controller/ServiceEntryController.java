package com.supecars.cardealermanagement.controller;

import com.supecars.cardealermanagement.model.Car;
import com.supecars.cardealermanagement.model.ServiceEntry;
import com.supecars.cardealermanagement.service.CarService;
import com.supecars.cardealermanagement.service.ServiceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ServiceEntryController {

    private final ServiceEntryService serviceEntryService;

    private final CarService carService;

    @Autowired
    public ServiceEntryController(ServiceEntryService serviceEntryService, CarService carService) {
        this.serviceEntryService = serviceEntryService;
        this.carService = carService;
    }

    @GetMapping("/services")
    public String getAllServices(Model model) {
        List<ServiceEntry> services = serviceEntryService.getAllRegisteredServices();
        model.addAttribute("services", services);
        return "services";
    }

    @GetMapping("/addService")
    public String addServiceEntryForm(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("serviceEntry", new ServiceEntry());
        return "addService";
    }

    @PostMapping("/services")
    public String addServiceEntry(@ModelAttribute ServiceEntry serviceEntry) {
        serviceEntryService.addNewServiceEntry(serviceEntry);
        return "redirect:/services";
    }

    @PostMapping("/services/delete/{id}")
    public String deleteServiceEntry(@PathVariable int id) {
        serviceEntryService.deleteServiceEntry(id);
        return "redirect:/services";
    }

    @PostMapping("/update_service/{id}")
    public String updateServiceEntry(@PathVariable int id, @ModelAttribute ServiceEntry serviceEntry) {
        serviceEntryService.updateServiceEntry(id, serviceEntry);
        return "redirect:/services";
    }

    @GetMapping("/editService/{id}")
    public String editServiceEntryForm(@PathVariable int id, Model model){
        ServiceEntry serviceEntry = serviceEntryService.getServiceEntryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory Id:" + id));
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("serviceEntry", serviceEntry);
        return "editService";
    }

}
