package com.company.implementations;

import com.company.strategies.PaymentDetails;
import com.company.strategies.PaymentResult;
import com.company.strategies.PaymentStrategy;

public class BankTransferPayment implements PaymentStrategy {
    private static final double TRANSFER_FEE = 1.50;

    @Override
    public PaymentResult processPayment(double amount, PaymentDetails details) {
        if (!validatePaymentDetails(details)) {
            return new PaymentResult(false, "Invalid bank account number", 0.0);
        }

        double totalAmount = amount + TRANSFER_FEE;

        boolean success = initiateBankTransfer(details, amount);

        if (success) {
            String message = String.format("Bank transfer initiated. Amount: $%.2f, Fee: $%.2f. Processing time: 1-3 business days",
                    amount, TRANSFER_FEE);
            return new PaymentResult(true, message, totalAmount);
        }

        return new PaymentResult(false, "Bank transfer failed", 0.0);
    }

    @Override
    public boolean validatePaymentDetails(PaymentDetails details) {
        String accountNumber = details.getIdentifier();
        return accountNumber != null &&
                accountNumber.matches("\\d{8,17}");
    }

    @Override
    public String getPaymentMethodName() {
        return "Bank Transfer";
    }

    private boolean initiateBankTransfer(PaymentDetails details, double amount) {
        return amount >= 10.0;
    }
}

