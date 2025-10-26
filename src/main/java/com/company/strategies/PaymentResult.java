package com.company.strategies;

public class PaymentResult {
    private final boolean success;
    private final String message;
    private final double totalAmount;

    public PaymentResult(boolean success, String message, double totalAmount) {
        this.success = success;
        this.message = message;
        this.totalAmount = totalAmount;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return String.format("PaymentResult{success=%s, message='%s', totalAmount=%.2f}",
                success, message, totalAmount);
    }
}
