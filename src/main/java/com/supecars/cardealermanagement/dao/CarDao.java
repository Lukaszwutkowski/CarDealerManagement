package com.supecars.cardealermanagement.dao;

import com.supecars.cardealermanagement.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarDao extends CrudRepository<Car, String> {
}
