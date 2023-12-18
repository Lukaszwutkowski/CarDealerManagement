package com.supecars.cardealermanagement.service;

import com.supecars.cardealermanagement.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(int id);
    void addNewEmployee(Employee employee);
    void deleteEmployee(int id);
    void updateEmployee(int id, Employee updatedEmployee);
}
