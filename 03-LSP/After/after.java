
interface NonWithdrawable {
    void deposit();
}
interface Withdrawable extends NonWithdrawable {
    void withdraw();
}

// SavingsAccount and CurrentAccount inherits both deposit and withdraw methods from Withdrawable  interface because it extends NonWithdrawable interface
// FixedDepositAccount inherits only deposit method from NonWithdrawable interface

class SavingsAccount implements Withdrawable {
    public void deposit(){
        //implementation
    }
    public void withdraw(){
        //implementation
    }
}
class CurrentAccount implements Withdrawable {
    public void deposit(){
        //implementatio
    }
    public void withdraw(){
        //implementatio
    }
}
class FixedDepositAccount implements NonWithdrawable {
    public void deposit(){
        //implementation
    }
}
class BankService {
    public void performTransaction(NonWithdrawable account){
        account.deposit();
    }
    public void performTransaction(Withdrawable account){
        account.withdraw();
    }
}

public class after {
    public static void main(String[] args){
        BankService service = new BankService();
        service.performTransaction(new FixedDepositAccount());
        service.performTransaction(new SavingsAccount());
    }
}
