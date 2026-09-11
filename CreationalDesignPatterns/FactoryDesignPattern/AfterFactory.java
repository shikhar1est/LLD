package FactoryDesignPattern;

// 1. Product Interface
interface PaymentMethod1 {
    void processPayment(double amount);
}

// 2. Concrete Products
class CreditCardPayment1 implements PaymentMethod1 {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment of $" + amount);
    }
}

class UPIPayment1 implements PaymentMethod1 {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing UPI payment of $" + amount);
    }
}

class PayPalPayment1 implements PaymentMethod1 {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

// 3. Factory Class
class PaymentFactory {
    public static PaymentMethod1 getPaymentMethod(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Payment type cannot be null or empty.");
        }

        switch (type.toUpperCase()) {
            case "CREDIT_CARD":
                return new CreditCardPayment1();
            case "UPI":
                return new UPIPayment1();
            case "PAYPAL":
                return new PayPalPayment1();
            default:
                throw new IllegalArgumentException("Unsupported payment type: " + type);
        }
    }
}

// 4. Decoupled Client Layer
public class AfterFactory {
    public void checkout(String paymentType, double amount) {
        // Client interacts only with the factory and the interface
        PaymentMethod1 payment = PaymentFactory.getPaymentMethod(paymentType);
        payment.processPayment(amount);
    }

    public static void main(String[] args) {
        AfterFactory service = new AfterFactory();
        service.checkout("UPI", 1499.0);
        service.checkout("CREDIT_CARD", 520.0);
    }
}