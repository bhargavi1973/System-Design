// defining an abstract class 
interface PaymentService{
    void pay();
}

class CreditCardPayment implements PaymentService{
    public void pay(){
        System.out.println("Paying through Credit Card logic");
    }
}
class DebitCardPayment implements PaymentService{
    public void pay(){
        System.out.println("Paying through Debit Card logic");
    }
}

class UPIPayment implements PaymentService{
    public void pay(){
        System.out.println("Paying through UPI logic");
    }
}

class NetBankingPayment implements PaymentService{
    public void pay(){
        System.out.println("Paying through Net Banking logic");
    }
}

// using a payment processor class, we don't need to explicitly know, the type of service user wants to use 
class PaymentProcessor{
  public void processPayment(PaymentMethod paymentMethod){
    paymentMethod.pay();
}
  
public class PaymentServiceSystem {
    public static void main(String[] args){
        // --------- without processor class-------
        PaymentService paymentService = new CreditCardPayment();
        paymentService.pay();

        paymentService = new DebitCardPayment();
        paymentService.pay();

        paymentService = new UPIPayment();
        paymentService.pay();

        paymentService = new NetBankingPayment();
        paymentService.pay();

        //--------------with processor class-------
        PaymentProcessor processor = new PaymentProcessor();
        processor.processPayment(new CreditCard());
        processor.processPayment(new DebitCard());
        
    }
}
