package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.CarDao;
import com.supecars.cardealermanagement.exception.CarNotFoundException;
import com.supecars.cardealermanagement.model.Car;
import com.supecars.cardealermanagement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CarServiceImpl implements CarService {

    private final CarDao carDao;

    @Autowired
    public CarServiceImpl(CarDao carDao){
        this.carDao = carDao;
    }

    @Override
    public List<Car> getAllCars() {
        return (List<Car>) carDao.findAll();
    }

    @Override
    public Optional<Car> getCarByVinNumber(String vinNumber) {
        return carDao.findById(vinNumber);
    }

    @Override
    public void addNewCar(Car car) {
        carDao.save(car);
    }

    @Override
    public void deleteCar(String vinNumber) {
        carDao.deleteById(vinNumber);
    }

    @Override
    public void updateCar(String vinNumber, Car updatedCar) {
        Car car = carDao.findById(vinNumber)
                .orElseThrow(() -> new CarNotFoundException("Car with VIN " + vinNumber + " not found"));

        updateCarDetails(car, updatedCar);

        carDao.save(car);

    }
    private void updateCarDetails(Car car, Car updatedCar) {
        Optional.ofNullable(updatedCar.getBrand()).ifPresent(car::setBrand);
        Optional.ofNullable(updatedCar.getModel()).ifPresent(car::setModel);
        Optional.ofNullable(updatedCar.getEngineType()).ifPresent(car::setEngineType);
        Optional.of(updatedCar.getEngineCapacity()).ifPresent(car::setEngineCapacity);
        Optional.of(updatedCar.getHorsepower()).ifPresent(car::setHorsepower);
        Optional.ofNullable(updatedCar.getColor()).ifPresent(car::setColor);
        Optional.of(updatedCar.getYearOfManufacture()).ifPresent(car::setYearOfManufacture);
        Optional.ofNullable(updatedCar.getTransmissionType()).ifPresent(car::setTransmissionType);
        Optional.of(updatedCar.getIsNew()).ifPresent(car::setIsNew);
        Optional.of(updatedCar.getMileage()).ifPresent(car::setMileage);
    }
}
