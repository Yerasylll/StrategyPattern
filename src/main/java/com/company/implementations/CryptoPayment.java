package com.company.implementations;

import com.company.strategies.PaymentDetails;
import com.company.strategies.PaymentResult;
import com.company.strategies.PaymentStrategy;

public class CryptoPayment implements PaymentStrategy {
    private static final double NETWORK_FEE = 5.0;

    @Override
    public PaymentResult processPayment(double amount, PaymentDetails details) {
        if (!validatePaymentDetails(details)) {
            return new PaymentResult(false, "Invalid cryptocurrency wallet address", 0.0);
        }

        double totalAmount = amount + NETWORK_FEE;

        boolean success = processBlockchainTransaction(details, amount);

        if (success) {
            String message = String.format("Cryptocurrency payment successful. Amount: $%.2f, Network Fee: $%.2f",
                    amount, NETWORK_FEE);
            return new PaymentResult(true, message, totalAmount);
        }

        return new PaymentResult(false, "Blockchain transaction failed", 0.0);
    }

    @Override
    public boolean validatePaymentDetails(PaymentDetails details) {
        String walletAddress = details.getIdentifier();
        return walletAddress != null &&
                walletAddress.length() >= 26 &&
                walletAddress.length() <= 42;
    }

    @Override
    public String getPaymentMethodName() {
        return "Cryptocurrency";
    }

    private boolean processBlockchainTransaction(PaymentDetails details, double amount) {
        return amount > 0 && details.getIdentifier().length() >= 26;
    }
}
