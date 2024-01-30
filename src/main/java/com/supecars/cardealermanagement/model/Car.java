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
    @Column(name = "EngineType")
    private String engineType;
    @Column(name = "EngineCapacity")
    private double engineCapacity;
    @Column(name = "HorsePower")
    private int horsepower;
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

    public Car(String vin, String brand, String model, String engineType, double engineCapacity,
               int horsepower, String color, int yearOfManufacture,
               TransmissionType transmissionType, BigDecimal price, boolean isNew,
               int mileage) {
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.engineType = engineType;
        this.engineCapacity = engineCapacity;
        this.horsepower = horsepower;
        this.color = color;
        this.yearOfManufacture = yearOfManufacture;
        this.transmissionType = transmissionType;
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

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
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

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
