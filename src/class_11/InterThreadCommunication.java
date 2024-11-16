package class_11;

import static java.lang.Thread.sleep;

public class InterThreadCommunication {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();

        new Thread(()->{
            try {
                sleep(5000);
                synchronized (obj){
                    obj.notify();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        System.out.println(Thread.currentThread().getName()+" started");
        synchronized (obj){
            obj.wait();
        }

        System.out.println(Thread.currentThread().getName()+" end");
    }
}
