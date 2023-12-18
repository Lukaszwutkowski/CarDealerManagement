package com.supecars.cardealermanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class Person {
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Street")
    private String street;
    @Column(name = "City")
    private String city;
    @Column(name = "PostalCode")
    private String postalCode;
    @Column(name = "Country")
    private String country;
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Column(name = "Email")
    private String email;

    public Person(String firstName, String lastName, String street, String city, String postalCode, String country, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Person() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
