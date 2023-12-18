package com.supecars.cardealermanagement.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Employees")
public class Employee extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployeeID")
    private int employeeId;
    @Column(name = "Position")
    private String position;
    @Column(name = "Salary", precision = 10, scale = 2)
    private BigDecimal salary;
    @Column(name = "HireDate")
    @Temporal(TemporalType.DATE)
    private Date hireDate;
    @OneToMany(mappedBy = "employee")
    private List<Sale> sales;

    public Employee(String firstName, String lastName, String street, String city, String postalCode, String country, String phoneNumber, String email, int employeeId, String position, BigDecimal salary, Date hireDate) {
        super(firstName, lastName, street, city, postalCode, country, phoneNumber, email);
        this.employeeId = employeeId;
        this.position = position;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public Employee() {
        super();

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
