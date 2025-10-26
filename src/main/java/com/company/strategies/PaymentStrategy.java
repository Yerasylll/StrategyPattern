package com.company.strategies;

public interface PaymentStrategy {
    PaymentResult processPayment(double amount, PaymentDetails details);
    boolean validatePaymentDetails(PaymentDetails details);
    String getPaymentMethodName();
}