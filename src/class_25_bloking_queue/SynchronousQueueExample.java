package class_25_bloking_queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueExample {

    public static void main(String[] args) {

        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        // Producer Thread
        new Thread(() -> {
            try {
                System.out.println("Producer trying to put: 10");

                queue.put(10);   // wait until consumer takes it

                System.out.println("Producer successfully transferred: 10");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        // Consumer Thread
        new Thread(() -> {
            try {

                Thread.sleep(3000);

                System.out.println("Consumer waiting to take data");

                Integer value = queue.take();  // receive directly

                System.out.println("Consumer received: " + value);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}