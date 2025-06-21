public class Girl extends Thread{
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println("Girl");
            try{
                Thread.sleep(10);
            } catch (InterruptedException e){
                System.out.println("Interrupted exception");
            }
        }
    }
}