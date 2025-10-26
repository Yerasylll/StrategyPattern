package com.company.implementations;

import com.company.strategies.PaymentDetails;
import com.company.strategies.PaymentResult;
import com.company.strategies.PaymentStrategy;

public class CreditCardPayment implements PaymentStrategy {
    private static final double TRANSACTION_FEE_PERCENTAGE = 2.5;

    @Override
    public PaymentResult processPayment(double amount, PaymentDetails details) {
        if (!validatePaymentDetails(details)) {
            return new PaymentResult(false, "Invalid credit card details", 0.0);
        }

        double transactionFee = calculateTransactionFee(amount);
        double totalAmount = amount + transactionFee;

        boolean success = simulatePaymentProcessing(details);

        if (success) {
            String message = String.format("Credit card payment successful. Amount: $%.2f, Fee: $%.2f",
                    amount, transactionFee);
            return new PaymentResult(true, message, totalAmount);
        }

        return new PaymentResult(false, "Credit card payment failed", 0.0);
    }

    @Override
    public boolean validatePaymentDetails(PaymentDetails details) {
        String cardNumber = details.getIdentifier();
        return cardNumber != null &&
                cardNumber.length() >= 13 &&
                cardNumber.length() <= 19 &&
                cardNumber.matches("\\d+");
    }

    @Override
    public String getPaymentMethodName() {
        return "Credit Card";
    }

    private double calculateTransactionFee(double amount) {
        return amount * (TRANSACTION_FEE_PERCENTAGE / 100);
    }

    private boolean simulatePaymentProcessing(PaymentDetails details) {
        return !details.getIdentifier().startsWith("0000");
    }
}
