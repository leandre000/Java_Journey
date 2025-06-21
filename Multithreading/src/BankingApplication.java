public class BankingApplication {
    public static void main(String[]args){
        BankAccount bank = new BankAccount();
        Runnable u1=()->{
            bank.deposit();
            bank.withdraw();
        };
        Runnable u2=()->{
            bank.deposit();
            bank.withdraw();
        };

        Thread tu1 = new Thread(u1);
        Thread tu2 = new Thread(u2);
        tu1.start();
        tu2.start();

        try {
            tu1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            tu2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("The final balance is:"+bank.balance+"$");

    }
}