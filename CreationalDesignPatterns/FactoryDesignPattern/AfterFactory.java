package FactoryDesignPattern;

// 1. Product Interface
interface PaymentMethod1 { //This creates a common family name : PaymentMethod1.
    // It says: "I don't care if you are UPI, Card, or PayPal. If you want to be treated as a PaymentMethod,
    // you MUST have a method called processPayment that takes a double."
    //This is just an agreement.
    void processPayment(double amount);
}

// 2. Concrete Products (Concrete Classes)
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
class PaymentFactory { //Return type is PaymentMethod1 (The Interface!!): It does not return UPIPayment or CreditCardPayment.
    // It returns the parent type (PaymentMethod).
    public static PaymentMethod1 getPaymentMethod(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Payment type cannot be null or empty.");
        }

        switch (type.toUpperCase()) {  //QUESTION :If return type is PaymentMethod...then
            // why are we returning "new UPIPayment" "new CredCardPayment"??   Solution-> Ans6 in Obsidian


            case "CREDIT_CARD":
                return new CreditCardPayment1(); //Because UPIPayment1 and CreditCardPayment1 both implement PaymentMethod,
        // Java allows either of them to be returned under that generic label.

            //The new keyword (creating the actual object) happens only here, hidden inside this one method.
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