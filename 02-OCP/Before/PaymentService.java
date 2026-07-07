// Here, we if we want to add new payment service then we need to change the PaymentService class 
// Large if-else makes the code less readable
// volates Open Closed Principle

class PaymentService{
    public void pay(String type){
        if(type.equals("Credit Card")){
            System.out.println("Paying through Credit Card");
        }
        else if(type.equals("Debit Card")){
            System.out.println("Paying through Debit Card");
        }
        else if(type.equals("UPI")){
            System.out.println("Paying through UPI");
        }
        else if(type.equals("Net Banking")){
            System.out.println("Paying throught Net Banking");
        }else{
            System.out.println("Invalid payment type");
        }
    }
}

public class PaymentServiceSystem {
    public static void main(String[] args){
        PaymentService paymentService = new PaymentService();
        paymentService.pay("Credit Card");
        paymentService.pay("Debit Card");
        paymentService.pay("UPI");
        paymentService.pay("Net Banking");
        paymentService.pay("Invalid Payment Type");
    }
}

