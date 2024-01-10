package com.supecars.cardealermanagement.controller;

import com.supecars.cardealermanagement.model.Car;
import com.supecars.cardealermanagement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getAllCars(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "cars";
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Car> getCarByVinNumber(@PathVariable String vin) {
        return carService.getCarByVinNumber(vin)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("addCar")
    public String addCarForm(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "addCar";
    }

    @PostMapping("/cars")
    public String addNewCar(@ModelAttribute Car car) {
        carService.addNewCar(car);
        return "redirect:/cars";
    }

    @PostMapping("/cars/delete/{vin}")
    public String deleteCar(@PathVariable String vin) {
        carService.deleteCar(vin);
        return "redirect:/cars";
    }

    @PutMapping("/{vin}")
    public ResponseEntity<Car> updateCar(@PathVariable String vin, @RequestBody Car car) {
        carService.updateCar(vin, car);
        return ResponseEntity.ok(car);
    }

}
