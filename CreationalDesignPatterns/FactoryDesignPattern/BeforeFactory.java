package FactoryDesignPattern;

// Concrete Implementations
class CreditCardPayment {
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment of $" + amount);
    }
}

class UPIPayment {
    public void processPayment(double amount) {
        System.out.println("Processing UPI payment of $" + amount);
    }
}

class PayPalPayment {
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

// Client / Service Layer directly instantiating dependencies
public class BeforeFactory {
    public void checkout(String paymentMethod, double amount) {
        if ("CREDIT_CARD".equalsIgnoreCase(paymentMethod)) {
            CreditCardPayment payment = new CreditCardPayment();
            payment.processPayment(amount);
        } else if ("UPI".equalsIgnoreCase(paymentMethod)) {
            UPIPayment payment = new UPIPayment();
            payment.processPayment(amount);
        } else if ("PAYPAL".equalsIgnoreCase(paymentMethod)) {
            PayPalPayment payment = new PayPalPayment();
            payment.processPayment(amount);
        } else {
            throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }

    public static void main(String[] args) {
        BeforeFactory service = new BeforeFactory();
        service.checkout("UPI", 1499.0);
        service.checkout("CREDIT_CARD", 520.0);
    }
}