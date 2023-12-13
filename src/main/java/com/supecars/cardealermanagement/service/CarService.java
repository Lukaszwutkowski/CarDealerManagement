package com.supecars.cardealermanagement.service;

import com.supecars.cardealermanagement.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCars();

    Optional<Car> getCarByVinNumber(String vinNumber);

    void addNewCar(Car car);

    void deleteCar(String vinNumber);

    void updateCar(String vinNumber, Car updatedCar);
}
