package com.supecars.cardealermanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Customers")
public class Customer extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private int customerId;

    public Customer(String firstName, String lastName, Address address, String phoneNumber, String email, int customerId) {
        super(firstName, lastName, address, phoneNumber, email);
        this.customerId = customerId;
    }

    public Customer() {
        super();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
