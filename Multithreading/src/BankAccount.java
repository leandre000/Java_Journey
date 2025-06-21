public class BankAccount {
    int balance=0;
    public synchronized void deposit(){
        balance = balance+1950;
        System.out.println("Deposited 1950$, new balance:"+balance);
    }
    public synchronized void withdraw(){
        balance = balance-500;
        System.out.println("Withdrew 500$, new balance:"+balance);
    }
}