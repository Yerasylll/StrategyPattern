package com.company.implementations;

import com.company.strategies.PaymentDetails;
import com.company.strategies.PaymentResult;
import com.company.strategies.PaymentStrategy;

public class PayPalPayment implements PaymentStrategy {
    private static final double FIXED_FEE = 0.30;
    private static final double PERCENTAGE_FEE = 2.9;

    @Override
    public PaymentResult processPayment(double amount, PaymentDetails details) {
        if (!validatePaymentDetails(details)) {
            return new PaymentResult(false, "Invalid PayPal email", 0.0);
        }

        double transactionFee = calculateTransactionFee(amount);
        double totalAmount = amount + transactionFee;

        boolean success = authenticatePayPalAccount(details);

        if (success) {
            String message = String.format("PayPal payment successful. Amount: $%.2f, Fee: $%.2f",
                    amount, transactionFee);
            return new PaymentResult(true, message, totalAmount);
        }

        return new PaymentResult(false, "PayPal authentication failed", 0.0);
    }

    @Override
    public boolean validatePaymentDetails(PaymentDetails details) {
        String email = details.getIdentifier();
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    @Override
    public String getPaymentMethodName() {
        return "PayPal";
    }

    private double calculateTransactionFee(double amount) {
        return FIXED_FEE + (amount * (PERCENTAGE_FEE / 100));
    }

    private boolean authenticatePayPalAccount(PaymentDetails details) {
        return details.getIdentifier().contains("@");
    }
}
