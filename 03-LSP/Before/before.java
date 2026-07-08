interface BankAccount{
    void deposit();
    void withdraw();
}
class SavingsAccount implements BankAccount{
    public  void deposit(){ 
        //implementation
    }
    public void withdraw(){ 
        //implementation
    }
}
class CurrentAccount implements BankAccount{
    public void deposit(){
        //implementation
    }
    public void withdraw(){
        //implementation
    }
}
class FixedDepositeAccount implements BankAccount{
    public void deposit(){
        //implementation
    }
    public void withdraw(){
        throw new UnsupportedOperationException("withdrawl not allowed");
    }
}
class BankService {
    public void performTransaction(BankAccount account){
        account.deposit();
        account.withdraw();
    }
}
public class before{
    public static void main(String[] args){
       BankService service = new BankService();
       service.performTransaction(new SavingsAccount());
       service.performTransaction(new CurrentAccount());
       service.performTransaction(new FixedDepositeAccount());    // will throw runtime exception
    }
}
