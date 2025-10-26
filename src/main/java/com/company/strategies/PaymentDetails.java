package com.company.strategies;

public class PaymentDetails {
    private final String identifier;
    private final String customerName;

    public PaymentDetails(String identifier, String customerName) {
        this.identifier = identifier;
        this.customerName = customerName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getCustomerName() {
        return customerName;
    }
}
