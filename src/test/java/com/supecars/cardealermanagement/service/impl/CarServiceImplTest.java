package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.CarDao;
import com.supecars.cardealermanagement.model.Car;
import com.supecars.cardealermanagement.model.TransmissionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;
    @Mock
    private CarDao carDao;

    private Car car1;
    private Car car2;

    @BeforeEach
    void setUp() {
        carDao = mock(CarDao.class);
        carService = new CarServiceImpl(carDao);
        car1 = new Car("VIN1", "Brand1", "Model1", "Diesel", 2.4, 165, "Color1", 2023, TransmissionType.AUTOMATIC,  true, 50);
        car2 = new Car("VIN2", "Brand2", "Model2", "Electric", 75.0, 150, "Color2", 2021, TransmissionType.AUTOMATIC, false, 52000);
    }

    @Test
    void it_should_return_all_cars() {
        //given
        when(carDao.findAll()).thenReturn(Arrays.asList(car1, car2));

        //when
        List<Car> cars = carService.getAllCars();

        //then
        assertNotNull(cars);
        assertEquals(2, cars.size());
    }

    @Test
    void it_should_return_car_by_vin_number() {
        //given
        when(carDao.findById(car1.getVin())).thenReturn(Optional.of(car1));

        //when
        Optional<Car> foundCar = carService.getCarByVinNumber(car1.getVin());

        //then
        assertTrue(foundCar.isPresent());
        assertEquals(car1.getVin(), foundCar.get().getVin());
        verify(carDao, times(1)).findById(car1.getVin());
    }

    @Test
    void it_should_add_new_car() {
        //given
        when(carDao.save(any(Car.class))).thenReturn(car1);

        //when
        carService.addNewCar(car1);

        //then
        verify(carDao, times(1)).save(car1);
    }

    @Test
    void it_should_delete_car_by_given_vin_number() {
        //given
        doNothing().when(carDao).deleteById(car1.getVin());

        //when
        carService.deleteCar(car1.getVin());

        //then
        verify(carDao, times(1)).deleteById(car1.getVin());
    }

    @Test
    void it_should_update_car() {
        when(carDao.findById(car1.getVin())).thenReturn(Optional.of(car1));
        when(carDao.save(any(Car.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Car updatedCar = new Car("VIN1", "UptadetBrand", "UpdatedModel", "Electric", 75.0, 150, "UpdatedColor", 2021, TransmissionType.AUTOMATIC, false, 52000);


        carService.updateCar(car1.getVin(), updatedCar);

        assertEquals(updatedCar.getBrand(), car1.getBrand());
        assertEquals(updatedCar.getModel(), car1.getModel());
        assertEquals(updatedCar.getEngineType(), car1.getEngineType());
        assertEquals(updatedCar.getEngineCapacity(), car1.getEngineCapacity());
        assertEquals(updatedCar.getHorsepower(), car1.getHorsepower());
        assertEquals(updatedCar.getColor(), car1.getColor());
        assertEquals(updatedCar.getYearOfManufacture(), car1.getYearOfManufacture());
        assertEquals(updatedCar.getTransmissionType(), car1.getTransmissionType());
        assertEquals(updatedCar.getIsNew(), car1.getIsNew());
        assertEquals(updatedCar.getMileage(), car1.getMileage());

        verify(carDao, times(1)).findById(car1.getVin());
        verify(carDao, times(1)).save(car1);
    }
}