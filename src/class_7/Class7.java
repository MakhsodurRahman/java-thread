package class_7;

import static java.lang.Thread.sleep;

public class Class7 {
    public static void main(String[] args) throws InterruptedException {

        Thread main = Thread.currentThread();
        Thread t = new Thread(()->{
            for (int i = 0; i<=100; i++){
                System.out.println(i+"  main thread state :: "+main.getState());
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        t.setDaemon(true);
        t.start();

        sleep(1000);
        System.out.println("main done");
    }
}
