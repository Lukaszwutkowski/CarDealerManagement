package com.supecars.cardealermanagement.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Cars")
public class Car {

    @Id
    @Column(name = "VIN", length = 17)
    private String vin;
    @Column(name = "Brand", length = 50)
    private String brand;
    @Column(name = "Model", length = 50)
    private String model;
    @Column(name = "Color", length = 50)
    private String color;
    @Column(name = "YearOfManufacture")
    private int yearOfManufacture;
    @Enumerated(EnumType.STRING)
    @Column(name = "TransmissionType")
    private TransmissionType transmissionType;
    @Column(name = "Price", precision = 10, scale = 2)
    private BigDecimal price;
    @Column(name = "IsNew")
    private boolean isNew;
    @Column(name = "Mileage")
    private int mileage;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<Sale> sales;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<ServiceEntry> serviceEntries;

    public Car(String vin, String brand, String model, String color, int yearOfManufacture, BigDecimal price, boolean isNew, int mileage) {
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.yearOfManufacture = yearOfManufacture;
        this.price = price;
        this.isNew = isNew;
        this.mileage = mileage;
    }

    public Car() {

    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
