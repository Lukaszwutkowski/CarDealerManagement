package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.EmployeeDao;
import com.supecars.cardealermanagement.exception.EmployeeNotFoundException;
import com.supecars.cardealermanagement.model.Employee;
import com.supecars.cardealermanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao){
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) employeeDao.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeDao.findById(id);
    }

    @Override
    public void addNewEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeDao.deleteById(id);
    }

    @Override
    public void updateEmployee(int id, Employee updatedEmployee) {
        Employee existingEmployee = employeeDao.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        updateEmployeeDetails(existingEmployee, updatedEmployee);

        employeeDao.save(existingEmployee);
        
    }

    private void updateEmployeeDetails(Employee existingEmployee, Employee updatedEmployee) {
        Optional.ofNullable(updatedEmployee.getFirstName()).ifPresent(existingEmployee::setFirstName);
        Optional.ofNullable(updatedEmployee.getLastName()).ifPresent(existingEmployee::setLastName);
        Optional.ofNullable(updatedEmployee.getStreet()).ifPresent(existingEmployee::setStreet);
        Optional.ofNullable(updatedEmployee.getCity()).ifPresent(existingEmployee::setCity);
        Optional.ofNullable(updatedEmployee.getPostalCode()).ifPresent(existingEmployee::setPostalCode);
        Optional.ofNullable(updatedEmployee.getCountry()).ifPresent(existingEmployee::setCountry);
        Optional.ofNullable(updatedEmployee.getPhoneNumber()).ifPresent(existingEmployee::setPhoneNumber);
        Optional.ofNullable(updatedEmployee.getEmail()).ifPresent(existingEmployee::setEmail);
        Optional.ofNullable(updatedEmployee.getPosition()).ifPresent(existingEmployee::setPosition);
        Optional.ofNullable(updatedEmployee.getSalary()).ifPresent(existingEmployee::setSalary);
        Optional.ofNullable(updatedEmployee.getHireDate()).ifPresent(existingEmployee::setHireDate);
    }
}
