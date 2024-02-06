package com.supecars.cardealermanagement.controller;

import com.supecars.cardealermanagement.model.Car;
import com.supecars.cardealermanagement.model.Inventory;
import com.supecars.cardealermanagement.service.CarService;
import com.supecars.cardealermanagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    private CarService carService;
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventories")
    public String getAllInventories(Model model) {
        List<Inventory> inventories = inventoryService.getAllInventories();
        model.addAttribute("inventories", inventories);
        return "inventories";
    }

    @GetMapping("/addInventory")
    public String addInventoryForm(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("inventory", new Inventory());
        return "addInventory";
    }

    @PostMapping("/inventories")
    public String addInventory(@ModelAttribute Inventory inventory, BindingResult bindingResult, Model model) {
        if (inventoryService.isVinAlreadyInInventory(inventory.getCar().getVin())){
            bindingResult.rejectValue("car.vin", "error.inventory", "This VIN is already in the inventory");
            model.addAttribute("cars", carService.getAllCars());
            return "addInventory";
        }
        inventoryService.addNewInventory(inventory);
        return "redirect:/inventories";
    }

    @PostMapping("/inventories/delete/{id}")
    public String deleteInventory(@PathVariable int id) {
        inventoryService.deleteInventory(id);
        return "redirect:/inventories";
    }

    @PostMapping("/update_inventory/{id}")
    public String updateInventory(@PathVariable int id, @ModelAttribute Inventory inventory) {
        inventoryService.updateInventory(id, inventory);
        return "redirect:/inventories";
    }

    @GetMapping("/editInventory/{id}")
    public String editInventoryForm(@PathVariable int id, Model model){
        Inventory inventory = inventoryService.getInventoryById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid inventory Id:" + id));
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("inventory", inventory);
        return "editInventory";
    }

}
