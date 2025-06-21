//public class Main {
//    public static void main(String[] args) {
//        Boy boy = new Boy();
//        Girl girl =new Girl();
//        boy.sitting();
//        girl.sitting();
//    }
//}


//public class Main {
//    public static void main(String[] args) {
//        Boy boy = new Boy();
//        Girl girl = new Girl();
//
//        boy.start();
//        girl.start();
//    }
//}


public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Runnable boy = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Boy");
                counter.increment();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable girl = () -> {
            for (int j = 0; j < 10; j++) {
                System.out.println("Girl");
                counter.increment();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread boyThread = new Thread(boy);
        Thread girlThread = new Thread(girl);

        boyThread.start();
        girlThread.start();
        try {
            boyThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            girlThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The counter is: " + counter.count);

    }
}