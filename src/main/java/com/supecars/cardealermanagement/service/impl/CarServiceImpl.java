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

        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setColor(updatedCar.getColor());
        car.setYearOfManufacture(updatedCar.getYearOfManufacture());
        car.setTransmissionType(updatedCar.getTransmissionType());
        car.setPrice(updatedCar.getPrice());
        car.setNew(updatedCar.isNew());
        car.setMileage(updatedCar.getMileage());

        carDao.save(car);

    }
}
