package class_25_bloking_queue;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueExample {

    public static void main(String[] args) throws Exception {

        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        // ----------- add() -----------
        queue.add(10);
        queue.add(20);
        System.out.println("After add(): " + queue);

        // ----------- offer() -----------
        queue.offer(30);
        System.out.println("After offer(): " + queue);

        // queue full now
        boolean result = queue.offer(40);
        System.out.println("Offer when full: " + result);

        // ----------- peek() -----------
        System.out.println("Peek element: " + queue.peek());

        // ----------- size() -----------
        System.out.println("Current size: " + queue.size());

        // ----------- remainingCapacity() -----------
        System.out.println("Remaining capacity: " + queue.remainingCapacity());

        // ----------- poll() -----------
        System.out.println("Poll element: " + queue.poll());
        System.out.println("After poll: " + queue);

        // ----------- remove() -----------
        System.out.println("Remove element: " + queue.remove());
        System.out.println("After remove: " + queue);

        // ----------- iterator() -----------
        System.out.println("Iterating elements:");
        for (Integer integer : queue) {
            System.out.println(integer);
        }

        // ----------- Producer Thread (put) -----------
        new Thread(() -> {
            try {

                System.out.println("Producer adding elements...");

                queue.put(100);
                System.out.println("Put: 100");

                queue.put(200);
                System.out.println("Put: 200");

                queue.put(300);
                System.out.println("Put: 300");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        Thread.sleep(2000);

        // ----------- take() -----------
        System.out.println("Take element: " + queue.take());
        System.out.println("Take element: " + queue.take());

        // ----------- clear() -----------
        queue.clear();
        System.out.println("After clear: " + queue);

    }
}