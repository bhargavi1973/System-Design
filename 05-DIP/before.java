class CreditCardPayment {
    public void pay(double amount){
        System.out.println("Paid " + amount + " using credit card.");
    }
}
class UPIPayment {
    public void pay(double amount){
        System.out.println("Paid " + amount + " using UPI.");
    }
}
// here ShoppingService is hardcoded with type of payment service 
class ShoppingService {
    private CreditCardPayment creditCardPayment = new CreditCardPayment();
    private UPIPayment upiPayment = new UPIPayment();
    public void checkout(double amount, String paymentMethod){
        if(paymentMethod.equals("credit_card")){
            creditCardPayment.pay(amount);
        } else if(paymentMethod.equals("upi")){
            upiPayment.pay(amount);
        }
    }
}
public class before {
    public static void main(String[] args) {
        ShoppingService shoppingService = new ShoppingService();
        shoppingService.checkout(100.0, "credit_card");
        shoppingService.checkout(100.0, "upi");
    }
}
