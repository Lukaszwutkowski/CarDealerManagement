package com.supecars.cardealermanagement.service.impl;

import com.supecars.cardealermanagement.dao.EmployeeDao;
import com.supecars.cardealermanagement.model.Address;
import com.supecars.cardealermanagement.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee1, employee2, updatedEmployee;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() throws ParseException {
        Address address1 = new Address(1, "Main street", "Holmestrand", "8080", "Norway");
        Address address2 = new Address(2, "New street", "Holmestrand", "8080", "Norway");
        Address address3 = new Address(3, "Updated street", "Holmestrand", "8080", "Norway");

        Date hireDate1 = format.parse("2022-04-01");
        Date hireDate2 = format.parse("2020-01-01");

        employee1 = new Employee("John", "Smith", address1, "557734527", "john.smith@example.com", 1, "Sales advisor", new BigDecimal("50000"), hireDate1);
        employee2 = new Employee("Jane", "Summer", address2, "556677890", "jane.summer@example.com", 2, "Sales Manager", new BigDecimal("70000"), hireDate2);
        updatedEmployee = new Employee("Jane", "Summer", address3, "556677890", "jane.summer@example.com", 2, "Sales Manager", new BigDecimal("70000"), hireDate2);
    }


    @Test
    void it_should_give_all_employees() {
        //given
        List<Employee> expectedEmployees = Arrays.asList(employee1, employee2);
        when(employeeDao.findAll()).thenReturn(expectedEmployees);

        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();

        //then
        assertNotNull(actualEmployees);
        assertEquals(expectedEmployees.size(), actualEmployees.size());
        verify(employeeDao, times(1)).findAll();
    }

    @Test
    void it_should_give_employee_by_id() {
        //given
        int employeeId = employee1.getEmployeeId();
        when(employeeDao.findById(employeeId)).thenReturn(Optional.of(employee1));

        //when
        Optional<Employee> foundEmployee = employeeService.getEmployeeById(employeeId);

        //then
        assertTrue(foundEmployee.isPresent());
        assertEquals(employee1, foundEmployee.get());
        verify(employeeDao, times(1)).findById(employeeId);
    }

    @Test
    void it_should_add_new_employee() {
        //given
        when(employeeDao.save(any(Employee.class))).thenReturn(employee1);

        //when
        employeeService.addNewEmployee(employee1);

        //then
        verify(employeeDao, times(1)).save(employee1);
    }

    @Test
    void it_should_delete_employee_by_id() {
        //given
        int employeeId = employee1.getEmployeeId();
        doNothing().when(employeeDao).deleteById(employeeId);

        //when
        employeeService.deleteEmployee(employeeId);

        //then
        verify(employeeDao, times(1)).deleteById(employeeId);
    }

    @Test
    void it_should_update_employee_by_id() {
        //given
        when(employeeDao.findById(employee1.getEmployeeId())).thenReturn(Optional.of(employee1));
        when(employeeDao.save(any(Employee.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //when
        employeeService.updateEmployee(employee1.getEmployeeId(), updatedEmployee);

        //then
        assertEquals(updatedEmployee.getFirstName(), employee1.getFirstName());
        assertEquals(updatedEmployee.getLastName(), employee1.getLastName());
        assertEquals(updatedEmployee.getPosition(), employee1.getPosition());
        assertEquals(updatedEmployee.getSalary(), employee1.getSalary());
        assertEquals(updatedEmployee.getHireDate(), employee1.getHireDate());

        verify(employeeDao, times(1)).findById(employee1.getEmployeeId());
        verify(employeeDao, times(1)).save(employee1);
    }
}