package com.supecars.cardealermanagement.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Table(name = "Inventories")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryID")
    private int inventoryId;
    @ManyToOne
    @JoinColumn(name = "VIN", referencedColumnName = "VIN")
    private Car car;
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;
    @Column(name = "AcquisitionDate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date acquisitionDate;
    @Column(name = "AcquisitionPrice", precision = 10, scale = 2)
    private BigDecimal acquisitionPrice;

    public Inventory(int inventoryId, Car car, Status status, Date acquisitionDate, BigDecimal acquisitionPrice) {
        this.inventoryId = inventoryId;
        this.car = car;
        this.status = status;
        this.acquisitionDate = acquisitionDate;
        this.acquisitionPrice = acquisitionPrice;
    }

    public Inventory() {

    }

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public BigDecimal getAcquisitionPrice() {
        return acquisitionPrice;
    }

    public void setAcquisitionPrice(BigDecimal acquisitionPrice) {
        this.acquisitionPrice = acquisitionPrice;
    }
}
