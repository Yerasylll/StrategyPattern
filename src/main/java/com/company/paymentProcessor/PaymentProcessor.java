package com.company.paymentProcessor;

import com.company.strategies.PaymentDetails;
import com.company.strategies.PaymentResult;
import com.company.strategies.PaymentStrategy;

public class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public PaymentProcessor() {
        this.paymentStrategy = null;
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Payment strategy cannot be null");
        }
        this.paymentStrategy = strategy;
    }

    public PaymentResult executePayment(double amount, PaymentDetails details) {
        if (paymentStrategy == null) {
            return new PaymentResult(false, "No payment strategy selected", 0.0);
        }

        if (amount <= 0) {
            return new PaymentResult(false, "Invalid payment amount", 0.0);
        }

        System.out.println("Processing payment using: " + paymentStrategy.getPaymentMethodName());
        return paymentStrategy.processPayment(amount, details);
    }

    public boolean validatePaymentMethod(PaymentDetails details) {
        if (paymentStrategy == null) {
            return false;
        }
        return paymentStrategy.validatePaymentDetails(details);
    }
}
