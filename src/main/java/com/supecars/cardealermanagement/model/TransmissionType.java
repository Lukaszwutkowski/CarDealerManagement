package com.supecars.cardealermanagement.model;

public enum TransmissionType {

    MANUAL("Manual"),
    AUTOMATIC("Automatic"),
    SEMI_AUTOMATIC("Semi-Automatic");

    private final String displayValue;

    TransmissionType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
