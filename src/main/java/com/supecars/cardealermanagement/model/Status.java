package com.supecars.cardealermanagement.model;

public enum Status {

    FOR_SALE("For Sale"),
    RESERVED("Reserved"),
    IN_SERVICE("In Service"),
    SOLD("Sold");

    private final String displayValue;

    Status(String displayValue) {
        this.displayValue = displayValue;
    }

}
