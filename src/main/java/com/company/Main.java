package com.company;

import com.company.implementations.BankTransferPayment;
import com.company.implementations.CreditCardPayment;
import com.company.implementations.CryptoPayment;
import com.company.implementations.PayPalPayment;
import com.company.paymentProcessor.PaymentProcessor;
import com.company.strategies.PaymentDetails;
import com.company.strategies.PaymentResult;

public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // Test 1: Credit Card Payment
        System.out.println("=== Credit Card Payment ===");
        processor.setPaymentStrategy(new CreditCardPayment());
        PaymentDetails creditCardDetails = new PaymentDetails("4532015112830366", "Yerassyl");
        PaymentResult result1 = processor.executePayment(100.00, creditCardDetails);
        System.out.println(result1);
        System.out.println();

        // Test 2: PayPal Payment
        System.out.println("=== PayPal Payment ===");
        processor.setPaymentStrategy(new PayPalPayment());
        PaymentDetails paypalDetails = new PaymentDetails("yerassyl.all@email.com", "Yerassyl Alimbek");
        PaymentResult result2 = processor.executePayment(75.50, paypalDetails);
        System.out.println(result2);
        System.out.println();

        // Test 3: Cryptocurrency Payment
        System.out.println("=== Cryptocurrency Payment ===");
        processor.setPaymentStrategy(new CryptoPayment());
        PaymentDetails cryptoDetails = new PaymentDetails("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "Yerassyl Alimbek");
        PaymentResult result3 = processor.executePayment(200.00, cryptoDetails);
        System.out.println(result3);
        System.out.println();

        // Test 4: Bank Transfer Payment
        System.out.println("=== Bank Transfer Payment ===");
        processor.setPaymentStrategy(new BankTransferPayment());
        PaymentDetails bankDetails = new PaymentDetails("12345678901234", "Yerassyl Alimbek");
        PaymentResult result4 = processor.executePayment(500.00, bankDetails);
        System.out.println(result4);
        System.out.println();

        // Test 5: Invalid Payment Details
        System.out.println("=== Invalid Credit Card ===");
        processor.setPaymentStrategy(new CreditCardPayment());
        PaymentDetails invalidCard = new PaymentDetails("invalid", "Yerassyl Alimbek");
        PaymentResult result5 = processor.executePayment(50.00, invalidCard);
        System.out.println(result5);
        System.out.println();

        // Test 6: Strategy Runtime Switching
        System.out.println("=== Runtime Strategy Switching ===");
        double amount = 150.00;
        PaymentDetails details = new PaymentDetails("4532015112830366", "Yerassyl Alimbek");

        System.out.println("Attempting payment with Credit Card first...");
        processor.setPaymentStrategy(new CreditCardPayment());
        PaymentResult creditResult = processor.executePayment(amount, details);

        if (!creditResult.isSuccess()) {
            System.out.println("Credit Card failed, switching to PayPal...");
            processor.setPaymentStrategy(new PayPalPayment());
            PaymentDetails paypalAlt = new PaymentDetails("yerassyl.all@email.com", "Yerassyl Alimbek");
            PaymentResult paypalResult = processor.executePayment(amount, paypalAlt);
            System.out.println(paypalResult);
        } else {
            System.out.println(creditResult);
        }

    }
}