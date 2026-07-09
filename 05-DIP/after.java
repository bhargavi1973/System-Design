// here we have created a interface PaymentProcessor alonng with a PaymentProcessorFactory factory class which currently violates OCP because we have to open it  to add a new payment service
interface PaymentProcessor {
    void processPayment(double amount);
}

class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid ₹" + amount + " using Credit Card.");
    }
}

class UPIPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Paid ₹" + amount + " using UPI.");
    }
}

class PaymentProcessorFactory {

    public static PaymentProcessor getPaymentProcessor(String paymentMethod) {

        if (paymentMethod.equalsIgnoreCase("credit_card")) {
            return new CreditCardPaymentProcessor();
        }

        if (paymentMethod.equalsIgnoreCase("upi")) {
            return new UPIPaymentProcessor();
        }

        throw new IllegalArgumentException("Invalid Payment Method");
    }
}

class ShoppingService {

    public void checkout(double amount, String paymentMethod) {

        PaymentProcessor paymentProcessor =
                PaymentProcessorFactory.getPaymentProcessor(paymentMethod);

        paymentProcessor.processPayment(amount);
    }
}

public class Main {

    public static void main(String[] args) {

        ShoppingService shoppingService = new ShoppingService();

        shoppingService.checkout(1000, "credit_card");
        shoppingService.checkout(500, "upi");
    }
}
