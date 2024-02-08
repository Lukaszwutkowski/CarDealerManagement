package com.supecars.cardealermanagement.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Services")
public class ServiceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceID")
    private int serviceId;
    @ManyToOne
    @JoinColumn(name = "VIN", referencedColumnName = "VIN")
    private Car car;
    @Column(name = "ServiceDate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date serviceDate;
    @Column(name = "Description", length = 255)
    private String description;
    @Column(name = "Cost", precision = 10, scale = 2)
    private BigDecimal cost;

    public ServiceEntry(int serviceId, Car car, Date serviceDate, String description, BigDecimal cost) {
        this.serviceId = serviceId;
        this.car = car;
        this.serviceDate = serviceDate;
        this.description = description;
        this.cost = cost;
    }

    public ServiceEntry() {

    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
