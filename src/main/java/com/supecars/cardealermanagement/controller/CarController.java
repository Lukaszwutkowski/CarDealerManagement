package com.supecars.cardealermanagement.controller;

import com.supecars.cardealermanagement.model.Car;
import com.supecars.cardealermanagement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Car> getCarByVinNumber(@PathVariable String vin) {
        return carService.getCarByVinNumber(vin)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Car> addNewCar(@RequestBody Car car) {
        carService.addNewCar(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity<Void> deleteCar(@PathVariable String vin) {
        carService.deleteCar(vin);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{vin}")
    public ResponseEntity<Car> updateCar(@PathVariable String vin, @RequestBody Car car) {
        carService.updateCar(vin, car);
        return ResponseEntity.ok(car);
    }

}
