public class Boy extends Thread{
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println("Boy");
            try{
                Thread.sleep(10);
            } catch (InterruptedException e){
                System.out.println("Interrupted exception");
            }
        }
    }
}





